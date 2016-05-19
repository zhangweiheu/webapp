package com.data.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhang on 2016/5/13.
 */
public class DataVO {
    private String algorithms;
    private String prune;
    private MultipartFile socialNet;
    private MultipartFile skillMatrix;
    private MultipartFile currentTeam;
    private String separatingEmployee;

    public String getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(String algorithms) {
        this.algorithms = algorithms;
    }

    public MultipartFile getSocialNet() {
        return socialNet;
    }

    public void setSocialNet(MultipartFile socialNet) {
        this.socialNet = socialNet;
    }

    public MultipartFile getSkillMatrix() {
        return skillMatrix;
    }

    public void setSkillMatrix(MultipartFile skillMatrix) {
        this.skillMatrix = skillMatrix;
    }

    public MultipartFile getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(MultipartFile currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String getSeparatingEmployee() {
        return separatingEmployee;
    }

    public void setSeparatingEmployee(String separatingEmployee) {
        this.separatingEmployee = separatingEmployee;
    }

    public String getPrune() {
        return prune;
    }

    public void setPrune(String prune) {
        this.prune = prune;
    }
}
