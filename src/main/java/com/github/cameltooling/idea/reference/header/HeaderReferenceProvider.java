package com.github.cameltooling.idea.reference.header;

import com.github.cameltooling.idea.reference.CamelPsiReferenceProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.patterns.PsiJavaElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteral;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public abstract class HeaderReferenceProvider extends PsiReferenceContributor {

    protected abstract PsiJavaElementPattern.Capture<PsiLiteral> getPattern();
    protected abstract PsiReference createReference(PsiElement element, String header);

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(getPattern(), new CamelPsiReferenceProvider() {
            @Override
            protected PsiReference[] getCamelReferencesByElement(PsiElement element, ProcessingContext context) {
                String header = StringUtil.unquoteString(element.getText());
                return new PsiReference[] { createReference(element, header) };
            }
        });
    }

}
