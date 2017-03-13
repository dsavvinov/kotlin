/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package org.jetbrains.kotlin.config

import com.intellij.openapi.util.text.StringUtil

class CompilerSettings {
    @JvmField var additionalArguments: String = DEFAULT_ADDITIONAL_ARGUMENTS
    @JvmField var scriptTemplates: String = ""
    @JvmField var scriptTemplatesClasspath: String = ""
    @JvmField var copyJsLibraryFiles: Boolean = true
    @JvmField var outputDirectoryForJsLibraryFiles: String = DEFAULT_OUTPUT_DIRECTORY

    companion object {
        val DEFAULT_ADDITIONAL_ARGUMENTS = "-version"
        private val DEFAULT_OUTPUT_DIRECTORY = "lib"
    }
}

val CompilerSettings.additionalArgumentsAsList: List<String>
    get() = StringUtil.splitHonorQuotes(additionalArguments, ' ').map {
        if (it.startsWith('"')) StringUtil.unescapeChar(StringUtil.unquoteString(it), '"') else it
    }