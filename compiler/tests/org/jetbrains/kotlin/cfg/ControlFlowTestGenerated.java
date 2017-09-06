/*
 * Copyright 2010-2017 JetBrains s.r.o.
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

package org.jetbrains.kotlin.cfg;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/cfg")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ControlFlowTestGenerated extends AbstractControlFlowTest {
    public void testAllFilesPresentInCfg() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
    }

    @TestMetadata("compiler/testData/cfg/arrays")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Arrays extends AbstractControlFlowTest {
        public void testAllFilesPresentInArrays() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/arrays"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("ArrayAccess.kt")
        public void testArrayAccess() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/ArrayAccess.kt");
            doTest(fileName);
        }

        @TestMetadata("arrayAccessExpression.kt")
        public void testArrayAccessExpression() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arrayAccessExpression.kt");
            doTest(fileName);
        }

        @TestMetadata("arrayInc.kt")
        public void testArrayInc() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arrayInc.kt");
            doTest(fileName);
        }

        @TestMetadata("arrayIncUnresolved.kt")
        public void testArrayIncUnresolved() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arrayIncUnresolved.kt");
            doTest(fileName);
        }

        @TestMetadata("ArrayOfFunctions.kt")
        public void testArrayOfFunctions() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/ArrayOfFunctions.kt");
            doTest(fileName);
        }

        @TestMetadata("arraySet.kt")
        public void testArraySet() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arraySet.kt");
            doTest(fileName);
        }

        @TestMetadata("arraySetNoRHS.kt")
        public void testArraySetNoRHS() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arraySetNoRHS.kt");
            doTest(fileName);
        }

        @TestMetadata("arraySetPlusAssign.kt")
        public void testArraySetPlusAssign() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arraySetPlusAssign.kt");
            doTest(fileName);
        }

        @TestMetadata("arraySetPlusAssignUnresolved.kt")
        public void testArraySetPlusAssignUnresolved() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arraySetPlusAssignUnresolved.kt");
            doTest(fileName);
        }

        @TestMetadata("arraySetUnresolved.kt")
        public void testArraySetUnresolved() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/arrays/arraySetUnresolved.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/basic")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Basic extends AbstractControlFlowTest {
        public void testAllFilesPresentInBasic() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/basic"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("Basic.kt")
        public void testBasic() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/basic/Basic.kt");
            doTest(fileName);
        }

        @TestMetadata("EmptyFunction.kt")
        public void testEmptyFunction() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/basic/EmptyFunction.kt");
            doTest(fileName);
        }

        @TestMetadata("ShortFunction.kt")
        public void testShortFunction() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/basic/ShortFunction.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/bugs")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Bugs extends AbstractControlFlowTest {
        public void testAllFilesPresentInBugs() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/bugs"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("jumpToOuterScope.kt")
        public void testJumpToOuterScope() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/bugs/jumpToOuterScope.kt");
            doTest(fileName);
        }

        @TestMetadata("kt10105.kt")
        public void testKt10105() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/bugs/kt10105.kt");
            doTest(fileName);
        }

        @TestMetadata("kt7761.kt")
        public void testKt7761() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/bugs/kt7761.kt");
            doTest(fileName);
        }

        @TestMetadata("setWithTypeMismatch.kt")
        public void testSetWithTypeMismatch() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/bugs/setWithTypeMismatch.kt");
            doTest(fileName);
        }

        @TestMetadata("unresolvedInvokeOnResolvedVar.kt")
        public void testUnresolvedInvokeOnResolvedVar() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/bugs/unresolvedInvokeOnResolvedVar.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/controlStructures")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class ControlStructures extends AbstractControlFlowTest {
        public void testAllFilesPresentInControlStructures() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/controlStructures"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("breakContinueInTryFinally.kt")
        public void testBreakContinueInTryFinally() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/breakContinueInTryFinally.kt");
            doTest(fileName);
        }

        @TestMetadata("breakInsideLocal.kt")
        public void testBreakInsideLocal() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/breakInsideLocal.kt");
            doTest(fileName);
        }

        @TestMetadata("continueInDoWhile.kt")
        public void testContinueInDoWhile() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/continueInDoWhile.kt");
            doTest(fileName);
        }

        @TestMetadata("continueInFor.kt")
        public void testContinueInFor() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/continueInFor.kt");
            doTest(fileName);
        }

        @TestMetadata("continueInWhile.kt")
        public void testContinueInWhile() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/continueInWhile.kt");
            doTest(fileName);
        }

        @TestMetadata("Finally.kt")
        public void testFinally() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/Finally.kt");
            doTest(fileName);
        }

        @TestMetadata("FinallyTestCopy.kt")
        public void testFinallyTestCopy() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/FinallyTestCopy.kt");
            doTest(fileName);
        }

        @TestMetadata("For.kt")
        public void testFor() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/For.kt");
            doTest(fileName);
        }

        @TestMetadata("If.kt")
        public void testIf() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/If.kt");
            doTest(fileName);
        }

        @TestMetadata("incorrectIndex.kt")
        public void testIncorrectIndex() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/incorrectIndex.kt");
            doTest(fileName);
        }

        @TestMetadata("InfiniteLoops.kt")
        public void testInfiniteLoops() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/InfiniteLoops.kt");
            doTest(fileName);
        }

        @TestMetadata("localAndNonlocalReturnsWithFinally.kt")
        public void testLocalAndNonlocalReturnsWithFinally() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/localAndNonlocalReturnsWithFinally.kt");
            doTest(fileName);
        }

        @TestMetadata("localFunctionInFinally.kt")
        public void testLocalFunctionInFinally() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/localFunctionInFinally.kt");
            doTest(fileName);
        }

        @TestMetadata("OnlyWhileInFunctionBody.kt")
        public void testOnlyWhileInFunctionBody() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/OnlyWhileInFunctionBody.kt");
            doTest(fileName);
        }

        @TestMetadata("returnsInWhen.kt")
        public void testReturnsInWhen() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/returnsInWhen.kt");
            doTest(fileName);
        }

        @TestMetadata("whenConditions.kt")
        public void testWhenConditions() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/whenConditions.kt");
            doTest(fileName);
        }

        @TestMetadata("whenExhaustive.kt")
        public void testWhenExhaustive() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/controlStructures/whenExhaustive.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/conventions")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Conventions extends AbstractControlFlowTest {
        public void testAllFilesPresentInConventions() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/conventions"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("bothReceivers.kt")
        public void testBothReceivers() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/conventions/bothReceivers.kt");
            doTest(fileName);
        }

        @TestMetadata("equals.kt")
        public void testEquals() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/conventions/equals.kt");
            doTest(fileName);
        }

        @TestMetadata("incrementAtTheEnd.kt")
        public void testIncrementAtTheEnd() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/conventions/incrementAtTheEnd.kt");
            doTest(fileName);
        }

        @TestMetadata("invoke.kt")
        public void testInvoke() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/conventions/invoke.kt");
            doTest(fileName);
        }

        @TestMetadata("notEqual.kt")
        public void testNotEqual() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/conventions/notEqual.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/deadCode")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class DeadCode extends AbstractControlFlowTest {
        public void testAllFilesPresentInDeadCode() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/deadCode"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("DeadCode.kt")
        public void testDeadCode() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/deadCode/DeadCode.kt");
            doTest(fileName);
        }

        @TestMetadata("notLocalReturn.kt")
        public void testNotLocalReturn() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/deadCode/notLocalReturn.kt");
            doTest(fileName);
        }

        @TestMetadata("returnInElvis.kt")
        public void testReturnInElvis() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/deadCode/returnInElvis.kt");
            doTest(fileName);
        }

        @TestMetadata("stringTemplate.kt")
        public void testStringTemplate() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/deadCode/stringTemplate.kt");
            doTest(fileName);
        }

        @TestMetadata("throwInLambda.kt")
        public void testThrowInLambda() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/deadCode/throwInLambda.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/declarations")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Declarations extends AbstractControlFlowTest {
        public void testAllFilesPresentInDeclarations() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("compiler/testData/cfg/declarations/classesAndObjects")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class ClassesAndObjects extends AbstractControlFlowTest {
            public void testAllFilesPresentInClassesAndObjects() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations/classesAndObjects"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("AnonymousInitializers.kt")
            public void testAnonymousInitializers() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/classesAndObjects/AnonymousInitializers.kt");
                doTest(fileName);
            }

            @TestMetadata("delegationByExpression.kt")
            public void testDelegationByExpression() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/classesAndObjects/delegationByExpression.kt");
                doTest(fileName);
            }

            @TestMetadata("delegationBySuperCall.kt")
            public void testDelegationBySuperCall() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/classesAndObjects/delegationBySuperCall.kt");
                doTest(fileName);
            }

            @TestMetadata("EnumEntryRefersCompanion.kt")
            public void testEnumEntryRefersCompanion() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/classesAndObjects/EnumEntryRefersCompanion.kt");
                doTest(fileName);
            }

            @TestMetadata("ObjectEnumQualifiers.kt")
            public void testObjectEnumQualifiers() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/classesAndObjects/ObjectEnumQualifiers.kt");
                doTest(fileName);
            }

            @TestMetadata("QualifierReceiverWithOthers.kt")
            public void testQualifierReceiverWithOthers() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/classesAndObjects/QualifierReceiverWithOthers.kt");
                doTest(fileName);
            }
        }

        @TestMetadata("compiler/testData/cfg/declarations/functionLiterals")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class FunctionLiterals extends AbstractControlFlowTest {
            public void testAllFilesPresentInFunctionLiterals() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations/functionLiterals"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("unusedFunctionLiteral.kt")
            public void testUnusedFunctionLiteral() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/functionLiterals/unusedFunctionLiteral.kt");
                doTest(fileName);
            }
        }

        @TestMetadata("compiler/testData/cfg/declarations/functions")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Functions extends AbstractControlFlowTest {
            public void testAllFilesPresentInFunctions() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations/functions"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("anonymousFunctionInBlock.kt")
            public void testAnonymousFunctionInBlock() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/functions/anonymousFunctionInBlock.kt");
                doTest(fileName);
            }

            @TestMetadata("FailFunction.kt")
            public void testFailFunction() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/functions/FailFunction.kt");
                doTest(fileName);
            }

            @TestMetadata("functionAsExpression.kt")
            public void testFunctionAsExpression() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/functions/functionAsExpression.kt");
                doTest(fileName);
            }

            @TestMetadata("namedFunctionInBlock.kt")
            public void testNamedFunctionInBlock() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/functions/namedFunctionInBlock.kt");
                doTest(fileName);
            }

            @TestMetadata("typeParameter.kt")
            public void testTypeParameter() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/functions/typeParameter.kt");
                doTest(fileName);
            }
        }

        @TestMetadata("compiler/testData/cfg/declarations/local")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Local extends AbstractControlFlowTest {
            public void testAllFilesPresentInLocal() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations/local"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("localClass.kt")
            public void testLocalClass() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/local/localClass.kt");
                doTest(fileName);
            }

            @TestMetadata("LocalDeclarations.kt")
            public void testLocalDeclarations() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/local/LocalDeclarations.kt");
                doTest(fileName);
            }

            @TestMetadata("localDelegatedVal.kt")
            public void testLocalDelegatedVal() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/local/localDelegatedVal.kt");
                doTest(fileName);
            }

            @TestMetadata("localFunction.kt")
            public void testLocalFunction() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/local/localFunction.kt");
                doTest(fileName);
            }

            @TestMetadata("localProperty.kt")
            public void testLocalProperty() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/local/localProperty.kt");
                doTest(fileName);
            }

            @TestMetadata("ObjectExpression.kt")
            public void testObjectExpression() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/local/ObjectExpression.kt");
                doTest(fileName);
            }
        }

        @TestMetadata("compiler/testData/cfg/declarations/multiDeclaration")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class MultiDeclaration extends AbstractControlFlowTest {
            public void testAllFilesPresentInMultiDeclaration() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations/multiDeclaration"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("MultiDecl.kt")
            public void testMultiDecl() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/multiDeclaration/MultiDecl.kt");
                doTest(fileName);
            }

            @TestMetadata("multiDeclarationWithError.kt")
            public void testMultiDeclarationWithError() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/multiDeclaration/multiDeclarationWithError.kt");
                doTest(fileName);
            }
        }

        @TestMetadata("compiler/testData/cfg/declarations/properties")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Properties extends AbstractControlFlowTest {
            public void testAllFilesPresentInProperties() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/declarations/properties"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
            }

            @TestMetadata("DelegatedProperty.kt")
            public void testDelegatedProperty() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/properties/DelegatedProperty.kt");
                doTest(fileName);
            }

            @TestMetadata("unreachableDelegation.kt")
            public void testUnreachableDelegation() throws Exception {
                String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/declarations/properties/unreachableDelegation.kt");
                doTest(fileName);
            }
        }
    }

    @TestMetadata("compiler/testData/cfg/expressions")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Expressions extends AbstractControlFlowTest {
        public void testAllFilesPresentInExpressions() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/expressions"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("assignmentToThis.kt")
        public void testAssignmentToThis() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/assignmentToThis.kt");
            doTest(fileName);
        }

        @TestMetadata("Assignments.kt")
        public void testAssignments() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/Assignments.kt");
            doTest(fileName);
        }

        @TestMetadata("callableReferences.kt")
        public void testCallableReferences() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/callableReferences.kt");
            doTest(fileName);
        }

        @TestMetadata("casts.kt")
        public void testCasts() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/casts.kt");
            doTest(fileName);
        }

        @TestMetadata("chainedQualifiedExpression.kt")
        public void testChainedQualifiedExpression() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/chainedQualifiedExpression.kt");
            doTest(fileName);
        }

        @TestMetadata("expressionAsFunction.kt")
        public void testExpressionAsFunction() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/expressionAsFunction.kt");
            doTest(fileName);
        }

        @TestMetadata("incdec.kt")
        public void testIncdec() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/incdec.kt");
            doTest(fileName);
        }

        @TestMetadata("invalidVariableCall.kt")
        public void testInvalidVariableCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/invalidVariableCall.kt");
            doTest(fileName);
        }

        @TestMetadata("labeledExpression.kt")
        public void testLabeledExpression() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/labeledExpression.kt");
            doTest(fileName);
        }

        @TestMetadata("LazyBooleans.kt")
        public void testLazyBooleans() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/LazyBooleans.kt");
            doTest(fileName);
        }

        @TestMetadata("nothingExpr.kt")
        public void testNothingExpr() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/nothingExpr.kt");
            doTest(fileName);
        }

        @TestMetadata("parenthesizedSelector.kt")
        public void testParenthesizedSelector() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/parenthesizedSelector.kt");
            doTest(fileName);
        }

        @TestMetadata("propertySafeCall.kt")
        public void testPropertySafeCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/propertySafeCall.kt");
            doTest(fileName);
        }

        @TestMetadata("qualifiedExpressionWithoutSelector.kt")
        public void testQualifiedExpressionWithoutSelector() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/qualifiedExpressionWithoutSelector.kt");
            doTest(fileName);
        }

        @TestMetadata("ReturnFromExpression.kt")
        public void testReturnFromExpression() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/ReturnFromExpression.kt");
            doTest(fileName);
        }

        @TestMetadata("thisExpression.kt")
        public void testThisExpression() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/thisExpression.kt");
            doTest(fileName);
        }

        @TestMetadata("unresolvedCall.kt")
        public void testUnresolvedCall() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unresolvedCall.kt");
            doTest(fileName);
        }

        @TestMetadata("unresolvedCalls.kt")
        public void testUnresolvedCalls() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unresolvedCalls.kt");
            doTest(fileName);
        }

        @TestMetadata("unresolvedCallsWithReceiver.kt")
        public void testUnresolvedCallsWithReceiver() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unresolvedCallsWithReceiver.kt");
            doTest(fileName);
        }

        @TestMetadata("unresolvedProperty.kt")
        public void testUnresolvedProperty() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unresolvedProperty.kt");
            doTest(fileName);
        }

        @TestMetadata("unresolvedWriteLHS.kt")
        public void testUnresolvedWriteLHS() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unresolvedWriteLHS.kt");
            doTest(fileName);
        }

        @TestMetadata("unsupportedReturns.kt")
        public void testUnsupportedReturns() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unsupportedReturns.kt");
            doTest(fileName);
        }

        @TestMetadata("unusedExpressionSimpleName.kt")
        public void testUnusedExpressionSimpleName() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/expressions/unusedExpressionSimpleName.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/functions")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Functions extends AbstractControlFlowTest {
        public void testAllFilesPresentInFunctions() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/functions"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("DefaultValuesForArguments.kt")
        public void testDefaultValuesForArguments() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/functions/DefaultValuesForArguments.kt");
            doTest(fileName);
        }

        @TestMetadata("unmappedArgs.kt")
        public void testUnmappedArgs() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/functions/unmappedArgs.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/secondaryConstructors")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class SecondaryConstructors extends AbstractControlFlowTest {
        public void testAllFilesPresentInSecondaryConstructors() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/secondaryConstructors"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("withPrimary.kt")
        public void testWithPrimary() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/secondaryConstructors/withPrimary.kt");
            doTest(fileName);
        }

        @TestMetadata("withPrimarySuper.kt")
        public void testWithPrimarySuper() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/secondaryConstructors/withPrimarySuper.kt");
            doTest(fileName);
        }

        @TestMetadata("withReturn.kt")
        public void testWithReturn() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/secondaryConstructors/withReturn.kt");
            doTest(fileName);
        }

        @TestMetadata("withoutPrimary.kt")
        public void testWithoutPrimary() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/secondaryConstructors/withoutPrimary.kt");
            doTest(fileName);
        }

        @TestMetadata("withoutPrimarySuper.kt")
        public void testWithoutPrimarySuper() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/secondaryConstructors/withoutPrimarySuper.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/tailCalls")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class TailCalls extends AbstractControlFlowTest {
        public void testAllFilesPresentInTailCalls() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/tailCalls"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("finally.kt")
        public void testFinally() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/tailCalls/finally.kt");
            doTest(fileName);
        }

        @TestMetadata("finallyWithReturn.kt")
        public void testFinallyWithReturn() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/tailCalls/finallyWithReturn.kt");
            doTest(fileName);
        }

        @TestMetadata("sum.kt")
        public void testSum() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/tailCalls/sum.kt");
            doTest(fileName);
        }

        @TestMetadata("try.kt")
        public void testTry() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/tailCalls/try.kt");
            doTest(fileName);
        }

        @TestMetadata("tryCatchFinally.kt")
        public void testTryCatchFinally() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/tailCalls/tryCatchFinally.kt");
            doTest(fileName);
        }
    }

    @TestMetadata("compiler/testData/cfg/withStdLib")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class WithStdLib extends AbstractControlFlowTest {
        public void testAllFilesPresentInWithStdLib() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/cfg/withStdLib"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("labeledReturns.kt")
        public void testLabeledReturns() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/cfg/withStdLib/labeledReturns.kt");
            doTest(fileName);
        }
    }
}
