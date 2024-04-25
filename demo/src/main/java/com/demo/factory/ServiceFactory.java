package com.demo.factory;

import com.demo.service.LoginService;

public interface ServiceFactory {
    LoginService createService(int role);
}
