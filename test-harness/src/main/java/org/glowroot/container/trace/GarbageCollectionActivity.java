/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.container.trace;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.base.MoreObjects;

import static org.glowroot.container.common.ObjectMappers.checkRequiredProperty;

public class GarbageCollectionActivity {

    private final long collectionCount;
    private final long collectionTimeMillis;

    private GarbageCollectionActivity(long collectionCount, long collectionTimeMillis) {
        this.collectionCount = collectionCount;
        this.collectionTimeMillis = collectionTimeMillis;
    }

    public long getCollectionCount() {
        return collectionCount;
    }

    public long getCollectionTimeMillis() {
        return collectionTimeMillis;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("collectionCount", collectionCount)
                .add("collectionTimeMillis", collectionTimeMillis)
                .toString();
    }

    @JsonCreator
    static GarbageCollectionActivity readValue(
            @JsonProperty("collectionCount") @Nullable Long collectionCount,
            @JsonProperty("collectionTimeMillis") @Nullable Long collectionTimeMillis)
                    throws JsonMappingException {
        checkRequiredProperty(collectionCount, "collectionCount");
        checkRequiredProperty(collectionTimeMillis, "collectionTimeMillis");
        return new GarbageCollectionActivity(collectionCount, collectionTimeMillis);
    }
}