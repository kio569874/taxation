package com.taxation.api.service;

import com.taxation.bean.EnterpriseEnrollDto;
import com.taxation.bean.EnterpriseEnrollEntity;
import com.taxation.bean.TempFileEntity;

import java.util.List;


public interface IEnterpriseEnrollService {
    /**
     * 保存公司服务信息
     * @param record
     * @return
     */
    boolean saveEnterpriseEnrollInfo(EnterpriseEnrollDto record);

    boolean insertTempFile(TempFileEntity record);
    /**
     * 根据providerId 查询对应的所有附件信息
     * @param providerId
     * @return
     */
    List<TempFileEntity> getTempFilesByProviderId(String providerId);

    /**
     * 根据providerId 删除对应的所有附件信息
     * @param providerId
     * @return
     */
    boolean deleteTempFilsByProviderId(String providerId);
}
