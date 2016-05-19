package com.data.service;

import java.util.HashMap;

/**
 * Created by zhang on 2016/5/12.
 */
public interface DataService {
    HashMap<String, Object> dataResolveByBasicAlgorithm(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath);

    HashMap<String, Object> dataResolveByFastExact(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath);

    HashMap<String, Object> dataResolveByFastApprox(String socialNetFilePath, String skillMatrixFilePath, String currentTeamFilePath);
}
