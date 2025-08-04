-- This is a migration file for creation of schemas and tables

create table IF NOT EXISTS payment_provider
(
    id          bigserial
        primary key,
    name        varchar(50) not null,
    description varchar(256)
);

create table IF NOT EXISTS payment_method
(
    id                   bigserial
        primary key,
    type                 varchar(32)                                         not null,
    created_at           timestamp   default now()                           not null,
    modified_at          timestamp,
    name                 varchar(64)                                         not null,
    is_active            boolean     default true,
    provider_unique_id   varchar(128)                                        not null,
    provider_method_type varchar(32)                                         not null,
    logo                 text,
    profile_type         varchar(24) default 'INDIVIDUAL'::character varying not null,
    payment_method_definitions_id bigserial,
    payment_method_required_fields_id uuid,
    provider_id          integer     default 1                               not null
        references payment_provider (id)
            on delete cascade
);

create table IF NOT EXISTS payment_method_definitions
(
    payment_method_id   bigint not null
        references payment_method (id)
            on delete cascade,
    currency_code       varchar(3),
    country_alpha3_code varchar(3),
    is_all_currencies   boolean default false,
    is_all_countries    boolean default false,
    is_priority         boolean,
    id                  bigserial
        primary key,
    is_active           boolean default true
);

create table IF NOT EXISTS payment_method_required_fields
(
    uid                 uuid      DEFAULT gen_random_uuid() not null
        primary key,
    created_at          timestamp default now()              not null,
    modified_at         timestamp default now()              not null,
    payment_method_id   bigint                               not null
        references payment_method(id),
    payment_type        varchar(64)                          not null,
    country_alpha3_code varchar(3),
    name                varchar(128)                         not null,
    data_type           varchar(128)                         not null,
    validation_type     varchar(128),
    validation_rule     varchar(128),
    default_value       varchar(128),
    values_options      text,
    description         varchar(255),
    placeholder         varchar(255),
    representation_name varchar(255),
    language            varchar(2),
    is_active           boolean   default true,
    constraint lang_name_method_id_type_country
        unique (language, name, payment_method_id, payment_type, country_alpha3_code)
);





