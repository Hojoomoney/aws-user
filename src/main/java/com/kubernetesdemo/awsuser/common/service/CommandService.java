package com.kubernetesdemo.awsuser.common.service;

import com.kubernetesdemo.awsuser.common.component.Messenger;

import java.sql.SQLException;

public interface CommandService<T> {
    Messenger save(T t) throws SQLException;
    Messenger deleteById(Long id);
    Messenger modify(T t);
}
