package com.example.wanandroid.network.cookie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import okhttp3.Cookie;

/**
 * @author gaofengpeng
 * @date 2019/7/31
 * @description :
 */

public class PersistentCookie implements Serializable {

    private transient Cookie cookie;
    private transient Cookie createCookie;

    public PersistentCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie getCreateCookie() {
        return createCookie;
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        //储存Cookie的信息
        outputStream.writeObject(cookie.name());
        outputStream.writeObject(cookie.value());

        outputStream.writeLong(cookie.expiresAt());

        outputStream.writeObject(cookie.domain());
        outputStream.writeObject(cookie.path());

        outputStream.writeBoolean(cookie.secure());
        outputStream.writeBoolean(cookie.httpOnly());
        outputStream.writeBoolean(cookie.persistent());
        outputStream.writeBoolean(cookie.hostOnly());
    }

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        String name = (String) inputStream.readObject();
        String value = (String) inputStream.readObject();

        Long expiresAt = inputStream.readLong();

        String domain = (String) inputStream.readObject();
        String path = (String) inputStream.readObject();

        Boolean secure = inputStream.readBoolean();
        Boolean httpOnly = inputStream.readBoolean();
        Boolean persistent = inputStream.readBoolean();
        Boolean hostOnly = inputStream.readBoolean();

        //恢复时创建Cookie类
        Cookie.Builder builder = new Cookie.Builder()
                .name(name)
                .value(value)
                .expiresAt(expiresAt)
                .domain(domain)
                .path(path);
        builder = secure ? builder.secure() : builder;
        builder = httpOnly ? builder.httpOnly() : builder;
        builder = hostOnly ? builder.hostOnlyDomain(domain) : builder.domain(domain);
        createCookie = builder.build();
    }
}
