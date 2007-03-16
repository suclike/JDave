/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jdave;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author Joni Freeman
 */
public class SerializableContract implements IContract {
    public void isSatisfied(Object obj) throws ExpectationFailedException {
        ObjectOutputStream stream = null;
        try {
            stream = new ObjectOutputStream(new OutputStream() {
                @Override
                public void write(int b) {                    
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            stream.writeObject(obj);
        } catch (IOException e) {
            throw new ExpectationFailedException(obj + " is not serializable");
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }
}
