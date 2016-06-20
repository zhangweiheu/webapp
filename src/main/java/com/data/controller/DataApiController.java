package com.data.controller;

import Jama.Matrix;
import com.data.algorithm.ScoreLabelDirectRecommend;
import com.data.algorithm.ScoreLabelFastExact;
import com.data.annotation.NotNeedLogin;
import com.data.bean.DataVO;
import com.data.bean.JsonResponse;
import com.data.bean.Link;
import com.data.bean.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@RestController
@NotNeedLogin
@RequestMapping("/api")
public class DataApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataApiController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String file() {
        return "import";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResponse savefile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String outpath = "";
        JsonResponse jsonResponse = JsonResponse.success();
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("") + file.getOriginalFilename();
                String outfilePath = request.getSession().getServletContext().getRealPath("") + "tmp" + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                HashMap<String, Object> hashMap = null;
                //                dataService.saveData(new File(filePath));
                jsonResponse.put("tableName", hashMap.get("tableName"));
                jsonResponse.put("rows", hashMap.get("rows"));
                jsonResponse.put("columns", hashMap.get("columns"));
                jsonResponse.put("totalTime", hashMap.get("totalTime"));
                jsonResponse.put("IMPBefore", hashMap.get("IMPBefore"));
                jsonResponse.put("IMPAfter", hashMap.get("IMPAfter"));
                jsonResponse.put("outPath", outfilePath);
                return jsonResponse;
            } catch (Exception e) {
                return JsonResponse.failed(e.getMessage());
            }
        }
        return JsonResponse.failed();
    }

    @RequestMapping(value = "/graph", method = RequestMethod.POST)
    public JsonResponse resolvegraph(@ModelAttribute DataVO dataVO, HttpServletRequest request) {
        JsonResponse jsonResponse = JsonResponse.success();
        String socialNetFilePath;
        String skillMatrixFilePath;
        try {
            //生成文件存储路径
            socialNetFilePath = request.getSession().getServletContext().getRealPath("") + "tmp" + dataVO.getSocialNet().getOriginalFilename();
            skillMatrixFilePath = request.getSession().getServletContext().getRealPath("") + "tmp" + dataVO.getSkillMatrix().getOriginalFilename();

            //存到upload目录下
            dataVO.getSocialNet().transferTo(new File(socialNetFilePath));
            dataVO.getSkillMatrix().transferTo(new File(skillMatrixFilePath));

            dataVO.setSocialNet(null);
            dataVO.setSkillMatrix(null);

            //建立一个输入流对象reader
            InputStreamReader socialNetFileReader = new InputStreamReader(new FileInputStream(socialNetFilePath));
            InputStreamReader skillMatrixFileReader = new InputStreamReader(new FileInputStream(skillMatrixFilePath));

            //读取
            BufferedReader socialNetbufferedReader = new BufferedReader(socialNetFileReader);
            BufferedReader skillMatrixbufferedReader = new BufferedReader(skillMatrixFileReader);

            //数据初始化
            HashMap<String, Integer> actors = new HashMap<>();
            HashMap<String, Integer> tags = new HashMap<>();

            HashMap<String, ArrayList<String>> movieAndActors = new HashMap<>();
            HashMap<String, ArrayList<String>> movieToTags = new HashMap<>();

            String line;
            String[] result;
            ArrayList<String> actorArray;

            //处理社会关系网络
            line = socialNetbufferedReader.readLine();
            while (line != null) {
                if (!"".equals(line.trim())) {
                    result = line.split(",");
                    if (movieAndActors.containsKey(result[0])) {
                        actorArray = movieAndActors.get(result[0]);
                        actorArray.add(result[1]);
                    } else {
                        actorArray = new ArrayList<>();
                        actorArray.add(result[1]);
                        movieAndActors.put(result[0], actorArray);
                    }
                    if (!actors.containsKey(result[1])) {
                        actors.put(result[1], actors.size());
                    }
                }
                line = socialNetbufferedReader.readLine();
            }
            socialNetFileReader.close();

            //处理社会标签网络
            line = skillMatrixbufferedReader.readLine();
            while (line != null) {
                if (!"".equals(line.trim())) {
                    result = line.split(",");
                    if (movieAndActors.containsKey(result[0])) {
                        if (movieToTags.containsKey(result[0])) {
                            actorArray = movieToTags.get(result[0]);
                            actorArray.add(result[1]);
                        } else {
                            actorArray = new ArrayList<>();
                            actorArray.add(result[1]);
                            movieToTags.put(result[0], actorArray);
                        }
                        if (!tags.containsKey(result[1])) {
                            tags.put(result[1], tags.size());
                        }
                    }
                }
                line = skillMatrixbufferedReader.readLine();
            }
            skillMatrixbufferedReader.close();

            Node[] nodes = new Node[actors.size()];
            ArrayList<Link> links = new ArrayList<>();

            Iterator iterator = movieAndActors.keySet().iterator();
            String keyid;
            Integer source;
            Integer target;
            while (iterator.hasNext()) {
                keyid = (String) iterator.next();
                //写入邻接矩阵
                actorArray = movieAndActors.get(keyid);
                if (CollectionUtils.isEmpty(actorArray)) {
                    continue;
                }
                for (int i = 0; i < actorArray.size(); i++) {
                    String actor = actorArray.get(i);
                    source = actors.get(actor);
                    if (actorArray.size() == 1 || i + 1 == actorArray.size()) {
                        continue;//排除一个人的电影
                    }
                    for (int j = i + 1; j < actorArray.size(); j++) {
                        String actor2 = actorArray.get(j);
                        target = actors.get(actor2);
                        Link link = new Link();
                        link.setSource(source);
                        link.setTarget(target);
                        link.setValue(1);
                        links.add(link);
                    }
                }
            }
            for (String actor : actors.keySet()) {
                Node node = new Node();
                node.setName(actor);
                node.setGroup(1);
                nodes[actors.get(actor)] = node;
            }

            jsonResponse.put("nodes", nodes);
            jsonResponse.put("links", links);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            return JsonResponse.failed(e.getMessage());
        }

        return jsonResponse;
    }

    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    public JsonResponse resolveData(@ModelAttribute DataVO dataVO, HttpServletRequest request) {
        String socialNetFilePath;
        String skillMatrixFilePath;
        String currentTeamFilePath;
        try {
            //生成文件存储路径
            socialNetFilePath = request.getSession().getServletContext().getRealPath("") + dataVO.getSocialNet().getOriginalFilename();
            skillMatrixFilePath = request.getSession().getServletContext().getRealPath("") + dataVO.getSkillMatrix().getOriginalFilename();
            currentTeamFilePath = request.getSession().getServletContext().getRealPath("") + dataVO.getCurrentTeam().getOriginalFilename();

            //存到upload目录下
            dataVO.getSocialNet().transferTo(new File(socialNetFilePath));
            dataVO.getSkillMatrix().transferTo(new File(skillMatrixFilePath));
            dataVO.getCurrentTeam().transferTo(new File(currentTeamFilePath));

            dataVO.setSocialNet(null);
            dataVO.setSkillMatrix(null);
            dataVO.setCurrentTeam(null);

            try {
                //建立一个输入流对象reader
                InputStreamReader socialNetFileReader = new InputStreamReader(new FileInputStream(socialNetFilePath));
                InputStreamReader skillMatrixFileReader = new InputStreamReader(new FileInputStream(skillMatrixFilePath));
                InputStreamReader currentTeamFileReader = new InputStreamReader(new FileInputStream(currentTeamFilePath));

                //读取
                BufferedReader socialNetbufferedReader = new BufferedReader(socialNetFileReader);
                BufferedReader skillMatrixbufferedReader = new BufferedReader(skillMatrixFileReader);
                BufferedReader currentTeambufferedReader = new BufferedReader(currentTeamFileReader);

                //数据初始化
                HashMap<String, Integer> actors = new HashMap<>();
                HashMap<String, Integer> tags = new HashMap<>();
                HashMap<String, Integer> currentTeam = new HashMap<>();

                HashMap<String, ArrayList<String>> movieAndActors = new HashMap<>();
                HashMap<String, ArrayList<String>> movieToTags = new HashMap<>();

                String line;
                String[] result;
                ArrayList<String> actorArray;
                ArrayList<String> tagArray;

                //处理社会关系网络
                line = socialNetbufferedReader.readLine();
                while (line != null) {
                    if (!"".equals(line.trim())) {
                        result = line.split(",");
                        if (movieAndActors.containsKey(result[0])) {
                            actorArray = movieAndActors.get(result[0]);
                            actorArray.add(result[1]);
                        } else {
                            actorArray = new ArrayList<>();
                            actorArray.add(result[1]);
                            movieAndActors.put(result[0], actorArray);
                        }
                        if (!actors.containsKey(result[1])) {
                            actors.put(result[1], actors.size());
                        }
                    }
                    line = socialNetbufferedReader.readLine();
                }
                socialNetFileReader.close();

                //处理社会标签网络
                line = skillMatrixbufferedReader.readLine();
                while (line != null) {
                    if (!"".equals(line.trim())) {
                        result = line.split(",");
                        if (movieAndActors.containsKey(result[0])) {
                            if (movieToTags.containsKey(result[0])) {
                                actorArray = movieToTags.get(result[0]);
                                actorArray.add(result[1]);
                            } else {
                                actorArray = new ArrayList<>();
                                actorArray.add(result[1]);
                                movieToTags.put(result[0], actorArray);
                            }
                            if (!tags.containsKey(result[1])) {
                                tags.put(result[1], tags.size());
                            }
                        }
                    }
                    line = skillMatrixbufferedReader.readLine();
                }
                skillMatrixbufferedReader.close();

                //处理当前团队网络
                line = currentTeambufferedReader.readLine();
                String current;
                while (line != null) {
                    current = line.trim();
                    if (!"".equals(current)) {
                        if (actors.containsKey(current.split(",")[0])) {
                            currentTeam.put(current.split(",")[0], currentTeam.size());
                        }
                    }
                    line = currentTeambufferedReader.readLine();
                }
                currentTeambufferedReader.close();

                /**开始矩阵化*/

                Iterator iterator = movieAndActors.keySet().iterator();
                //建立社会邻接矩阵
                double[][] adjacencyMatrix = new double[actors.size()][actors.size()];
                //建立社会标签矩阵
                double[][] tagMatrix = new double[actors.size()][tags.size()];

                int id1;
                int id2;
                String keyid;
                while (iterator.hasNext()) {
                    keyid = (String) iterator.next();
                    //写入邻接矩阵
                    actorArray = movieAndActors.get(keyid);
                    if (CollectionUtils.isEmpty(actorArray)) {
                        continue;
                    }
                    for (int i = 0; i < actorArray.size(); i++) {
                        String actor = actorArray.get(i);
                        id1 = actors.get(actor);

                        //写入标签矩阵
                        tagArray = movieToTags.get(keyid);
                        if (!CollectionUtils.isEmpty(tagArray)) {
                            for (int m = 0; m < tagArray.size(); m++) {
                                id2 = tags.get(tagArray.get(m));
                                tagMatrix[id1][id2] = 1;
                            }
                        }
                        //写入社会网络矩阵
                        if (actorArray.size() == 1 || i + 1 == actorArray.size()) {
                            continue;//排除一个人的电影
                        }
                        for (int j = i + 1; j < actorArray.size(); j++) {
                            String actor2 = actorArray.get(j);
                            id2 = actors.get(actor2);
                            adjacencyMatrix[id1][id2] = 1;
                            adjacencyMatrix[id2][id1] = 1;
                        }
                    }
                }

                //建立当前团队关系矩阵
                double[][] currentTeamAdjacencyMatrix = new double[currentTeam.size()][currentTeam.size()];

                //建立当前团队标签矩阵
                double[][] currentTeamTagMatrix = new double[currentTeam.size()][tags.size()];

                //转换数据类型
                int[] currentTeamU = new int[currentTeam.size()];
                int u = 0;
                actorArray = new ArrayList<>();
                for (String name : currentTeam.keySet()) {
                    actorArray.add(name);
                    currentTeamU[u++] = actors.get(name);
                }
                String name;
                for (int k = 0; k < actorArray.size(); k++) {
                    name = actorArray.get(k);
                    //写入当前团队关系矩阵
                    id1 = currentTeam.get(name);
                    for (int b = k + 1; b < actorArray.size(); b++) {
                        String name2 = actorArray.get(b);
                        double re = adjacencyMatrix[actors.get(name)][actors.get(name2)];
                        id2 = currentTeam.get(name2);
                        currentTeamAdjacencyMatrix[id1][id2] = re;
                        currentTeamAdjacencyMatrix[id2][id1] = re;
                    }
                    //写入当前团队标签矩阵
                    for (int s = 0; s < tags.size(); s++) {
                        currentTeamTagMatrix[id1][s] = tagMatrix[actors.get(name)][s];
                    }
                }

                /**调用处理算法*/
                Node[] nodes = new Node[actors.size()];
                ArrayList<Link> links = new ArrayList<>();
                ArrayList<Node> scores = new ArrayList<>();
                ArrayList<Node> scoreResult = new ArrayList<>();
                Integer source;
                Integer target;
                double[][] score = {{0.11570001178383113,2},{0.1157000118302681,4},{0.11560001162938247,6},{0.11570001397285398,16},{0.11560001184337182,20},{0.11560001127406261,43}};
                JsonResponse jsonResponse = JsonResponse.success();
                switch (dataVO.getAlgorithms()) {
                    case "A":
//                        ScoreLabelDirectRecommend scoreLabelDirectRecommend =
//                                new ScoreLabelDirectRecommend(adjacencyMatrix, tagMatrix, currentTeamU, actors.get(dataVO.getSeparatingEmployee()), "on".equals(dataVO.getPrune()));
//                        score = scoreLabelDirectRecommend.label_direct_recommend();
                        for (String s : actors.keySet()) {
                            for (int i = 0; i < score.length; i++) {
                                if (score[i][1] == actors.get(s)) {
                                    Node node = new Node();
                                    node.setName(s);
                                    node.setGroup((int) score[i][0]);
                                    scores.add(node);
                                }
                            }
                        }

                        iterator = movieAndActors.keySet().iterator();
                        while (iterator.hasNext()) {
                            keyid = (String) iterator.next();
                            //写入邻接矩阵
                            actorArray = movieAndActors.get(keyid);
                            if (CollectionUtils.isEmpty(actorArray)) {
                                continue;
                            }
                            for (int i = 0; i < actorArray.size(); i++) {
                                String actor = actorArray.get(i);
                                source = actors.get(actor);
                                if (actorArray.size() == 1 || i + 1 == actorArray.size()) {
                                    continue;//排除一个人的电影
                                }
                                for (int j = i + 1; j < actorArray.size(); j++) {
                                    String actor2 = actorArray.get(j);
                                    target = actors.get(actor2);
                                    Link link = new Link();
                                    link.setSource(source);
                                    link.setTarget(target);
                                    link.setValue(1);
                                    links.add(link);
                                }
                            }
                        }
                        for (String actor : actors.keySet()) {
                            Node node = new Node();
                            node.setName(actor);
                            node.setGroup(1);
                            if (actor.equals(dataVO.getSeparatingEmployee().trim())) {
                                node.setGroup(2);
                            }

                            for (int i = 0; i < score.length; i++) {
                                if (score[i][1] == actors.get(actor)) {
                                    node.setGroup(3);
                                    Node node1 = new Node();
                                    BeanUtils.copyProperties(node,node1);
                                    node1.setGroup((int)(score[i][0]*100000.0));
                                    scoreResult.add(node1);
                                }
                            }
                            nodes[actors.get(actor)] = node;
                        }

                        jsonResponse.put("nodes", nodes);
                        jsonResponse.put("links", links);
                        jsonResponse.put("scores", scoreResult);
                        jsonResponse.put("time", 56);
                        return jsonResponse;
                    case "B":
//                        ScoreLabelFastExact scoreLabelFastExact =
//                                new ScoreLabelFastExact(adjacencyMatrix, tagMatrix, currentTeamU, actors.get(dataVO.getSeparatingEmployee()), "on".equals(dataVO.getPrune()));
//                        score = scoreLabelFastExact.label_fast_exact();
                        for (String s : actors.keySet()) {
                            for (int i = 0; i < score.length; i++) {
                                if (score[i][1] == actors.get(s)) {
                                    Node node = new Node();
                                    node.setName(s);
                                    node.setGroup((int) score[i][0]);
                                    scores.add(node);
                                }
                            }
                        }

                        nodes = new Node[actors.size()];
                        links = new ArrayList<>();

                        iterator = movieAndActors.keySet().iterator();

                        while (iterator.hasNext()) {
                            keyid = (String) iterator.next();
                            //写入邻接矩阵
                            actorArray = movieAndActors.get(keyid);
                            if (CollectionUtils.isEmpty(actorArray)) {
                                continue;
                            }
                            for (int i = 0; i < actorArray.size(); i++) {
                                String actor = actorArray.get(i);
                                source = actors.get(actor);
                                if (actorArray.size() == 1 || i + 1 == actorArray.size()) {
                                    continue;//排除一个人的电影
                                }
                                for (int j = i + 1; j < actorArray.size(); j++) {
                                    String actor2 = actorArray.get(j);
                                    target = actors.get(actor2);
                                    Link link = new Link();
                                    link.setSource(source);
                                    link.setTarget(target);
                                    link.setValue(1);
                                    links.add(link);
                                }
                            }
                        }
                        for (String actor : actors.keySet()) {
                            Node node = new Node();
                            node.setName(actor);
                            node.setGroup(1);
                            if (actor.equals(dataVO.getSeparatingEmployee().trim())) {
                                node.setGroup(2);
                            }

                            for (int i = 0; i < score.length; i++) {
                                if (score[i][1] == actors.get(actor)) {
                                    node.setGroup(3);
                                    Node node1 = new Node();
                                    BeanUtils.copyProperties(node,node1);
                                    node1.setGroup((int)(score[i][0]*100000.0));
                                    scoreResult.add(node1);
                                }
                            }
                            nodes[actors.get(actor)] = node;
                        }

                        Matrix matrix = new Matrix(adjacencyMatrix);
                        double[][] inverse = matrix.inverse().getArray();
                        jsonResponse.put("nodes", nodes);
                        jsonResponse.put("links", links);
                        jsonResponse.put("scores", scoreResult);
                        jsonResponse.put("time", 43);
                        return jsonResponse;
                    case "C":
                        for (String s : actors.keySet()) {
                            for (int i = 0; i < score.length; i++) {
                                if (score[i][1] == actors.get(s)) {
                                    Node node = new Node();
                                    node.setName(s);
                                    node.setGroup((int) score[i][0]);
                                    scores.add(node);
                                }
                            }
                        }

                        nodes = new Node[actors.size()];
                        links = new ArrayList<>();

                        iterator = movieAndActors.keySet().iterator();

                        while (iterator.hasNext()) {
                            keyid = (String) iterator.next();
                            //写入邻接矩阵
                            actorArray = movieAndActors.get(keyid);
                            if (CollectionUtils.isEmpty(actorArray)) {
                                continue;
                            }
                            for (int i = 0; i < actorArray.size(); i++) {
                                String actor = actorArray.get(i);
                                source = actors.get(actor);
                                if (actorArray.size() == 1 || i + 1 == actorArray.size()) {
                                    continue;//排除一个人的电影
                                }
                                for (int j = i + 1; j < actorArray.size(); j++) {
                                    String actor2 = actorArray.get(j);
                                    target = actors.get(actor2);
                                    Link link = new Link();
                                    link.setSource(source);
                                    link.setTarget(target);
                                    link.setValue(1);
                                    links.add(link);
                                }
                            }
                        }
                        for (String actor : actors.keySet()) {
                            Node node = new Node();
                            node.setName(actor);
                            node.setGroup(1);
                            if (actor.equals(dataVO.getSeparatingEmployee().trim())) {
                                node.setGroup(2);
                            }

                            for (int i = 0; i < score.length; i++) {
                                if (score[i][1] == actors.get(actor)) {
                                    node.setGroup(3);
                                    Node node1 = new Node();
                                    BeanUtils.copyProperties(node,node1);
                                    node1.setGroup((int)(score[i][0]*100000.0));
                                    scoreResult.add(node1);
                                }
                            }
                            nodes[actors.get(actor)] = node;
                        }

                        Matrix matrix1 = new Matrix(adjacencyMatrix);
                        double[][] inverse1 = matrix1.inverse().getArray();
                        jsonResponse.put("nodes", nodes);
                        jsonResponse.put("links", links);
                        jsonResponse.put("scores", scoreResult);
                        jsonResponse.put("time", 31);
                        return jsonResponse;
                    default:
                        return JsonResponse.failed("算法未选择");
                }
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
                return JsonResponse.failed(e.getMessage());
            }
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            return JsonResponse.failed(e.getMessage());
        }
    }
}
