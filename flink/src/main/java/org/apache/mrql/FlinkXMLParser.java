/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.mrql;

import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.flink.core.fs.FSDataInputStream;


/** A parser for line-oriented, character delimited text files (such as CVS) */
final public class FlinkXMLParser extends XMLParser implements FlinkParser {
    FlinkXMLSplitter splitter;

   public void open ( String file ) {
        try {
            splitter = new FlinkXMLSplitter(tags,file);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public void open ( FSDataInputStream fsin, long start, long end ) {
        try {
            splitter = new FlinkXMLSplitter(tags,fsin,start,end);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public String slice () {
        if (splitter.hasNext()) {
            DataOutputBuffer b = splitter.next();
            return new String(b.getData(),0,b.getLength());
        } else return null;
   }
}
