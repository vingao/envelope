/**
 * Copyright © 2016-2017 Cloudera, Inc.
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
package com.cloudera.labs.envelope.plan;

import java.util.Set;

import com.typesafe.config.Config;

/**
 * Planners determine the mutations required to be applied to the output for the data of the step.
 * Custom planners should not implement Planner directly -- they should implement BulkPlanner
 * or RandomPlanner.
 */
public interface Planner {

  /**
   * Configure the planner.
   */
  void configure(Config config);

  /**
   * Get the set of mutation types that the planner may emit.
   */
  Set<MutationType> getEmittedMutationTypes();

}
