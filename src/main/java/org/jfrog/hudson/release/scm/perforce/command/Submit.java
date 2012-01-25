/*
 * Copyright (C) 2012 JFrog Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jfrog.hudson.release.scm.perforce.command;

import com.tek42.perforce.Depot;
import com.tek42.perforce.PerforceException;
import com.tek42.perforce.parse.AbstractPerforceTemplate;
import com.tek42.perforce.parse.Builder;

import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Submit the default change set.
 *
 * @author Yossi Shaul
 * @see <a href="http://www.perforce.com/perforce/doc.current/manuals/cmdref/submit.html">http://www.perforce.com/perforce/doc.current/manuals/cmdref/submit.html</a>
 */
public class Submit extends AbstractPerforceTemplate {
    private static Logger debuggingLogger = Logger.getLogger(Submit.class.getName());

    public Submit(Depot depot) {
        super(depot);
    }

    /**
     * Submit the default change set.
     */
    public void submit(String message) throws PerforceException {
        debuggingLogger.log(Level.FINE, "Submitting default changelist");
        SubmitBuilder builder = new SubmitBuilder(message);
        saveToPerforce(this, builder);
    }

    public static class SubmitBuilder implements Builder<Submit> {

        private final String message;

        public SubmitBuilder(String message) {
            this.message = message;
        }

        public String[] getSaveCmd(String p4exe, Submit edit) {
            return new String[]{p4exe, "-s", "submit", "-f", "revertunchanged", "-d", message};
        }

        public boolean requiresStandardInput() {
            return false;
        }

        public String[] getBuildCmd(String p4exe, String id) {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public Submit build(StringBuilder sb) throws PerforceException {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public void save(Submit obj, Writer writer) throws PerforceException {
            throw new UnsupportedOperationException("Operation not supported");
        }
    }
}
