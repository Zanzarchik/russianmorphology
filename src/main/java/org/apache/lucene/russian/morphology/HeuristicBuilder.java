/**
 * Copyright 2009 Alexander Kuznetsov 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.lucene.russian.morphology;

import org.apache.lucene.russian.morphology.dictonary.DictonaryReader;
import org.apache.lucene.russian.morphology.dictonary.FrequentyReader;
import org.apache.lucene.russian.morphology.dictonary.GrammaReader;
import org.apache.lucene.russian.morphology.dictonary.IgnoredFormReader;

import java.io.IOException;
import java.util.Set;


public class HeuristicBuilder {
    public static void main(String[] args) throws IOException {
        IgnoredFormReader formReader = new IgnoredFormReader("data/igoredFrom.txt");
        Set<String> form = formReader.getIngnoredFroms();

        FrequentyReader frequentyReader = new FrequentyReader("data/lemma.num");
        GrammaReader grammaInfo = new GrammaReader("dictonary/Dicts/Morph/rgramtab.tab");
        DictonaryReader dictonaryReader = new DictonaryReader("dictonary/Dicts/SrcMorph/RusSrc/morphs.mrd", form);

        StatiticsCollector statiticsCollector = new StatiticsCollector(grammaInfo);
        dictonaryReader.proccess(statiticsCollector);
        statiticsCollector.saveHeuristic();

    }
}
