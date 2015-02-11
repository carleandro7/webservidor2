/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.great.gcm.server;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * GCM message.
 
 */
public final class Message implements Serializable {

  private final String collapseKey;
  private final Boolean delayWhileIdle;
  private final Integer timeToLive;
  private Map<String, String> data;

  public static final class Builder {

    private Map<String, String> data;

    // optional parameters
    private String collapseKey;
    private Boolean delayWhileIdle;
    private Integer timeToLive;

    public Builder() {
      this.data = new LinkedHashMap<String, String>();
    }

    /**
     * Sets the collapseKey property.
       * @param value String
       * @return Builder
     */
    public Builder collapseKey(String value) {
      collapseKey = value;
      return this;
    }

    /**
     * Sets the delayWhileIdle property (default value is {@literal false}).
       * @param value boolean
       * @return Builder
     */
    public Builder delayWhileIdle(boolean value) {
      delayWhileIdle = value;
      return this;
    }

    /**
     * Sets the time to live, in seconds.
       * @param value int
       * @return  Builder
     */
    public Builder timeToLive(int value) {
      timeToLive = value;
      return this;
    }

    /**
     * Adds a key/value pair to the payload data.
       * @param key String
       * @param value String
       * @return Builder
     */
    public Builder addData(String key, String value) {
      data.put(key, value);
      return this;
    }
    
    public Builder setData(Map<String, String> dataParametros) {
        data = dataParametros;
       return this; 
    }

    public Message build() {
      return new Message(this);
    }

  }

  private Message(Builder builder) {
    collapseKey = builder.collapseKey;
    delayWhileIdle = builder.delayWhileIdle;
    data = Collections.unmodifiableMap(builder.data);
    timeToLive = builder.timeToLive;
  }

  /**
   * Gets the collapse key.
     * @return String
   */
  public String getCollapseKey() {
    return collapseKey;
  }

  /**
   * Gets the delayWhileIdle flag.
     * @return Boolean
   */
  public Boolean isDelayWhileIdle() {
    return delayWhileIdle;
  }

  /**
   * Gets the time to live (in seconds).
     * @return Integer
   */
  public Integer getTimeToLive() {
    return timeToLive;
  }

  /**
   * Gets the payload data, which is immutable.
     * @return Map String, String
   */
  public Map<String, String> getData() {
    return data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Message(");
    if (collapseKey != null) {
      builder.append("collapseKey=").append(collapseKey).append(", ");
    }
    if (timeToLive != null) {
      builder.append("timeToLive=").append(timeToLive).append(", ");
    }
    if (delayWhileIdle != null) {
      builder.append("delayWhileIdle=").append(delayWhileIdle).append(", ");
    }
    if (!data.isEmpty()) {
      builder.append("data: {");
      for (Map.Entry<String, String> entry : data.entrySet()) {
        builder.append(entry.getKey()).append("=").append(entry.getValue())
            .append(",");
      }
      builder.delete(builder.length() - 1, builder.length());
      builder.append("}");
    }
    if (builder.charAt(builder.length() - 1) == ' ') {
      builder.delete(builder.length() - 2, builder.length());
    }
    builder.append(")");
    return builder.toString();
  }

}
