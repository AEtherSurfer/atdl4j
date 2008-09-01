package br.com.investtools.fix.atdl.ui.swt.validation;

import java.util.Map;

import br.com.investtools.fix.atdl.ui.swt.ParameterWidget;
import br.com.investtools.fix.atdl.ui.swt.ValidationException;
import br.com.investtools.fix.atdl.valid.xmlbeans.OperatorT.Enum;
import br.com.investtools.fix.atdl.valid.xmlbeans.StrategyEditDocument.StrategyEdit;

/**
 * Validator that validates input against another existing field.
 * 
 * @author renato.gallart
 * 
 */
public class Field2OperatorValidationRule extends
		AbstractOperatorValidationRule {

	private String field;

	private Enum operator;

	private String field2;

	public Field2OperatorValidationRule(String field, Enum operator,
			String field2) {
		this.field = field;
		this.operator = operator;
		this.field2 = field2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(StrategyEdit strategyEdit,
			Map<String, ValidationRule> rules,
			Map<String, ParameterWidget<?>> widgets) throws ValidationException {

		// get the widget from context using field name
		ParameterWidget<?> widget = widgets.get(field);
		if (widget == null) {
			throw new ValidationException(null,
					"No widget defined for field \"" + field
							+ "\" in this context");
		}
		Object fieldValue = widget.getValue();

		// get the widget from context using field2 name
		ParameterWidget<?> widget2 = widgets.get(field2);
		if (widget2 == null) {
			throw new ValidationException(null,
					"No widget defined for field2 \"" + field2
							+ "\" in this context");
		}
		Object fieldValue2 = widget2.getValue();

		// compare both values
		validateValues(strategyEdit, widget, fieldValue, operator, fieldValue2);
	}

}