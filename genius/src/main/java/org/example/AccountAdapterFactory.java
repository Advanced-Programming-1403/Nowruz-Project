package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.RuntimeTypeAdapterFactory;

public class AccountAdapterFactory {
    public static Gson getAccountGson() {
        RuntimeTypeAdapterFactory<Account> accountAdapter =
                RuntimeTypeAdapterFactory.of(Account.class, "type")
                        .registerSubtype(User.class, "user")
                        .registerSubtype(Artist.class, "artist")
                        .registerSubtype(Admin.class, "admin");

        return new GsonBuilder()
                .registerTypeAdapterFactory(accountAdapter)
                .registerTypeAdapter(java.time.LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }
}
