package com.library.service;

import com.library.dao.AdminDao;
import com.library.dao.ReaderCardDao;
import com.library.dao.ReaderInfoDao;
import com.library.domain.ReaderCard;
import com.library.domain.ReaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ReaderCardDao readerCardDao;
    @Autowired
    private ReaderInfoDao readerInfoDao;
    @Autowired
    private AdminDao adminDao;

    public boolean hasMatchReader(long readerId,String password){
        return  readerCardDao.getIdMatchCount(readerId, password)>0;
    }

    public ReaderCard findReaderCardByReaderId(long readerId){
        return readerCardDao.findReaderByReaderId(readerId);
    }
    public ReaderInfo findReaderInfoByReaderId(long readerId){
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }

    public boolean hasMatchAdmin(long adminId,String password){
        return adminDao.getIdMatchCount(adminId,password)==1;
    }

    public boolean adminRePassword(long adminId, String newPassword){
        return adminDao.resetPassword(adminId,newPassword)>0;
    }
    public String getAdminPassword(long adminId){
        return adminDao.getPassword(adminId);
    }



}
