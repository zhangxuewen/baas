package com.baas.dal.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * TODO 查询结果封装�? * @author zhongcai.szc
 *
 * @param <T>
 */
public class PaginatedResult<T> implements Serializable {

    private static final long serialVersionUID = 652191324878008121L;
    /**
     */
    private int  recordCount;
    /**
     */
    private List<T> data;
    
    public PaginatedResult() {
        this(0, null);
    }
    
    public PaginatedResult(int recordCount, List<T> data) {
        this.recordCount = recordCount;
        this.data = data;
    }
    
    public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
