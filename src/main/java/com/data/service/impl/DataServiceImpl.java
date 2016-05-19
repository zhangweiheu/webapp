package com.data.service.impl;

import com.data.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by zhang on 2016/5/12.
 */
@Service
public class DataServiceImpl implements DataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);

    @Override
    public HashMap<String, Object> dataResolveByBasicAlgorithm(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath) {
        return null;
    }

    @Override
    public HashMap<String, Object> dataResolveByFastExact(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath) {
        return null;
    }

    @Override
    public HashMap<String, Object> dataResolveByFastApprox(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath) {
        return null;
    }

    private void commonDataResolve(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath) {


    }
}
