; Copyright 2009 yogthos; Lars Gregori
;
; Licensed under the Apache License, Version 2.0 (the "License"); you may
; not use this file except in compliance with the License. You may obtain
; a copy of the License at
;
; http://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
; WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
; License for the specific language governing permissions and limitations
; under the License.

(ns test.test
   (:require (ga [main :as ga])) 
  (:gen-class )) 
  
;example usage
(defn -main [args]
  (let [target (vec "Hello World!")
        mutator (struct ga/mutator-struct (fn[_] (for [i (range 0 (count target))] (char (ga/rand-in-range 32 126)))) {})]

    (defn get-fitness 
      "custom comparator function for evaluating fitness of members"
      [value]
      (if (not (nil? args)) (println value))
      (reduce - 0 (map #(if (= (first %1) (second %1)) 0 1) 
        (ga/zip value target))))       
         
    ;run the evolution function and print the result  
    (time (println (apply str (:value @(first (ga/evolve 500 0.01 mutator get-fitness))))))))
  
(-main nil)
