package com.github.cameltooling.idea.reference.header;

import com.intellij.patterns.PsiJavaElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.psi.PsiReference;

import static com.intellij.patterns.PsiJavaPatterns.psiLiteral;
import static com.intellij.patterns.PsiJavaPatterns.psiMethod;

public class GetHeaderReferenceProvider extends HeaderReferenceProvider {

    @Override
    protected PsiJavaElementPattern.Capture<PsiLiteral> getPattern() {
        return psiLiteral().methodCallParameter(
                        0, psiMethod()
                                .definedInClass("org.apache.camel.builder.BuilderSupport")
                                .withName("header"));
    }

    @Override
    protected PsiReference createReference(PsiElement element, String header) {
        return new GetHeaderReference(element, header);
    }

}
