package com.lior.sq.client;

import java.util.Arrays;
import java.util.List;

public class PersistedClasses
{
  public static List<Class<?>> list() {
    Class<?>[] classes = { GameHistoryDO.class };

    return Arrays.asList(classes);
  }
}
