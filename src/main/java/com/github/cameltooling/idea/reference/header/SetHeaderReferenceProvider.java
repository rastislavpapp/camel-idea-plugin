package com.github.cameltooling.idea.reference.header;

import com.github.cameltooling.idea.util.CamelIdeaUtils;
import com.intellij.patterns.PsiJavaElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.psi.PsiReference;

import static com.intellij.patterns.PsiJavaPatterns.psiLiteral;
import static com.intellij.patterns.PsiJavaPatterns.psiMethod;

public class SetHeaderReferenceProvider extends HeaderReferenceProvider {

    @Override
    protected PsiJavaElementPattern.Capture<PsiLiteral> getPattern() {
        return psiLiteral().methodCallParameter(
                0, psiMethod()
                        .definedInClass(CamelIdeaUtils.PROCESSOR_DEFINITION)
                        .withName("setHeader")
        );
    }

    @Override
    protected PsiReference createReference(PsiElement element, String header) {
        return new SetHeaderReference(element, header);
    }

}
