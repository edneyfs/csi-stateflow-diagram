/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.eclipse.core.runtime.IStatus
 *  org.eclipse.core.runtime.Status
 *  org.eclipse.emf.ecore.EClassifier
 *  org.eclipse.emf.ecore.EDataType
 *  org.eclipse.emf.ecore.EEnum
 *  org.eclipse.emf.ecore.EEnumLiteral
 *  org.eclipse.emf.ecore.util.EcoreUtil
 */
package com.csi.gmf.executionflow.diagram.expressions;

import com.csi.gmf.executionflow.diagram.part.ExecutionflowDiagramEditorPlugin;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Map;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.util.EcoreUtil;

public abstract class ExecutionflowAbstractExpression {
    private IStatus status = Status.OK_STATUS;
    private final String myBody;
    private final EClassifier myContext;

    protected void setStatus(int severity, String message, Throwable throwable) {
        String pluginID = "com.csi.gmf.executionflow.diagram";
        this.status = new Status(severity, pluginID, -1, message != null ? message : "", throwable);
        if (!this.status.isOK()) {
            ExecutionflowDiagramEditorPlugin.getInstance().logError("Expression problem:" + message + "body:" + this.body(), throwable);
        }
    }

    protected abstract Object doEvaluate(Object var1, Map var2);

    public Object evaluate(Object context) {
        return this.evaluate(context, Collections.EMPTY_MAP);
    }

    public Object evaluate(Object context, Map env) {
        if (this.context().isInstance(context)) {
            try {
                return this.doEvaluate(context, env);
            }
            catch (Exception e) {
                ExecutionflowDiagramEditorPlugin.getInstance().logError("Expression evaluation failure: " + this.body(), e);
            }
        }
        return null;
    }

    public static Object performCast(Object value, EDataType targetType) {
        if (targetType instanceof EEnum && value instanceof EEnumLiteral) {
            EEnumLiteral literal = (EEnumLiteral)value;
            return literal.getInstance() != null ? literal.getInstance() : literal;
        }
        if (!(value instanceof Number) || targetType == null || targetType.getInstanceClass() == null) {
            return value;
        }
        Class targetClass = targetType.getInstanceClass();
        Number num = (Number)value;
        Class<?> valClass = value.getClass();
        Class targetWrapperClass = targetClass;
        if (targetClass.isPrimitive()) {
            targetWrapperClass = EcoreUtil.wrapperClassFor((Class)targetClass);
        }
        if (valClass.equals(targetWrapperClass)) {
            return value;
        }
        if (Number.class.isAssignableFrom(targetWrapperClass)) {
            if (targetWrapperClass.equals(Byte.class)) {
                return new Byte(num.byteValue());
            }
            if (targetWrapperClass.equals(Integer.class)) {
                return new Integer(num.intValue());
            }
            if (targetWrapperClass.equals(Short.class)) {
                return new Short(num.shortValue());
            }
            if (targetWrapperClass.equals(Long.class)) {
                return new Long(num.longValue());
            }
            if (targetWrapperClass.equals(BigInteger.class)) {
                return BigInteger.valueOf(num.longValue());
            }
            if (targetWrapperClass.equals(Float.class)) {
                return new Float(num.floatValue());
            }
            if (targetWrapperClass.equals(Double.class)) {
                return new Double(num.doubleValue());
            }
            if (targetWrapperClass.equals(BigDecimal.class)) {
                return new BigDecimal(num.doubleValue());
            }
        }
        return value;
    }

    public IStatus getStatus() {
        return this.status;
    }

    public String body() {
        return this.myBody;
    }

    public EClassifier context() {
        return this.myContext;
    }

    protected ExecutionflowAbstractExpression(String body, EClassifier context) {
        this.myBody = body;
        this.myContext = context;
    }
}

