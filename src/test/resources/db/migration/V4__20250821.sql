-- Payment method test values
INSERT INTO payment_method(id, type, name, provider_unique_id, provider_method_type, logo,
                           payment_method_definitions_id, provider_id)
VALUES (1, 'TOP_UP', 'fake-provider-top-up', 'fake_provider', 'TOP_UP', 'logo.txt', 1, 1);

INSERT INTO payment_method(id, type, name, provider_unique_id, provider_method_type, logo,
                           payment_method_definitions_id, provider_id)
VALUES (2, 'PAY_OUT', 'fake-provider-pay-out', 'fake_provider', 'PAY_OUT', 'logo.txt', 1, 1);


-- Payment method definitions test values

INSERT INTO payment_method_definitions(payment_method_id, currency_code, country_alpha3_code, is_all_currencies,
                                       is_all_countries, is_priority, id)
VALUES (1, 'TST', 'TST', false, false, false, 1);

INSERT INTO payment_method_definitions(payment_method_id, currency_code, country_alpha3_code, is_all_currencies,
                                       is_all_countries, is_priority, id)
VALUES (2, 'TST', 'TST', false, false, false, 2);


-- Payment method required fields test values
-- TOP UP
INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'first_name', 'string', 'regexp', '^[A-Z][a-z]{1,49}$', 'John', 'string', 'First name',
        'string', 'First name', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'last_name', 'string', 'regexp', '^[A-Z][a-z]{1,49}$', 'Doe', 'string', 'Last name',
        'string', 'Last name', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'country', 'string', 'regexp', '^[A-Z]{3}$', 'Russia', 'string', 'Country', 'string',
        'Country', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'card_number', 'string', 'regexp', '^(\d{4}[- ]?){3}\d{4}$', '1234 5678 1234 5678',
        'string', 'Card number', 'string', 'Card number', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'cvv', 'string', 'regexp', '^\d{3}$', '999', 'string', 'CVV', 'string', 'CVV', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'expiration_date', 'string', 'regexp', '^(0[1-9]|1[0-2])/\d{2}$', '01/2000', 'string',
        'Expiration date', 'string', 'Expiration date', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'amount', 'double', 'regexp', '^\d+([,.]\d{1,2})?$', '01/2000', 'double', 'Amount',
        'double', 'Amount', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, values_options, description, placeholder,
                                           representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'password', 'string', 'regexp', '^.{0,32}$', 'string', 'Password', 'string', 'Password',
        'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'username', 'string', 'regexp', '^.{0,32}$', 'johndoe', 'string', 'Username', 'string',
        'Password', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, values_options, description,
                                           placeholder, representation_name, language)
VALUES (1, 'TOP_UP', 'TST', 'account_id', 'UUID', 'regexp',
        '^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$', 'UUID', 'Account ID', 'UUID',
        'Account ID', 'en');


-- PAY OUT
INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'first_name', 'string', 'regexp', '^[A-Z][a-z]{1,49}$', 'John', 'string', 'First name', 'string',
        'First name', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'last_name', 'string', 'regexp', '^[A-Z][a-z]{1,49}$', 'Doe', 'string', 'Last name',
        'string', 'Last name', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'country', 'string', 'regexp', '^[A-Z]{3}$', 'Russia', 'string', 'Country', 'string',
        'Country', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'card_number', 'string', 'regexp', '^(\d{4}[- ]?){3}\d{4}$', '1234 5678 1234 5678',
        'string', 'Card number', 'string', 'Card number', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'amount', 'double', 'regexp', '^\d+([,.]\d{1,2})?$', '01/2000', 'double', 'Amount',
        'double', 'Amount', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, values_options, description, placeholder,
                                           representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'password', 'string', 'regexp', '^.{0,32}$', 'string', 'Password', 'string', 'Password',
        'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, default_value, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'username', 'string', 'regexp', '^.{0,32}$', 'johndoe', 'string', 'Username', 'string',
        'Password', 'en');

INSERT INTO payment_method_required_fields(payment_method_id, payment_type, country_alpha3_code, name, data_type,
                                           validation_type, validation_rule, values_options, description,
                                           placeholder, representation_name, language)
VALUES (2, 'PAY_OUT', 'TST', 'account_id', 'UUID', 'regexp',
        '^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$', 'UUID', 'Account ID', 'UUID',
        'Account ID', 'en');