create table tenants
(
    id varchar(255) primary key,

    deleted boolean not null,

    created_at timestamp(6) not null,
    updated_at timestamp(6),

    admin_email varchar(255) not null unique,
    admin_full_name varchar(255) not null,
    admin_password varchar(255) not null,
    admin_username varchar(255) not null unique,

    company_code varchar(255) not null unique,
    company_name varchar(255) not null,

    email varchar(255) not null unique,

    status varchar(50) not null
        check (
            status in (
                       'PENDING',
                       'ACTIVE',
                       'SUSPENDED',
                       'INACTIVE'
                )
            )
);

create table users
(
    id varchar(255) primary key,

    deleted boolean not null,
    enabled boolean,

    created_at timestamp(6) not null,
    updated_at timestamp(6),

    created_by varchar(255) not null,
    updated_by varchar(255),

    email varchar(255) not null unique,

    first_name varchar(255) not null,
    last_name varchar(255) not null,

    username varchar(255) not null unique,

    password varchar(255) not null,

    role varchar(100) not null
        check (
            role in (
                     'ROLE_PLATFORM_ADMIN',
                     'ROLE_COMPANY_ADMIN',
                     'ROLE_ADMINISTRATOR',
                     'ROLE_USER',
                     'ROLE_SALES_OPERATOR'
                )
            ),

    tenant_id varchar(255),

    constraint fk_user_tenant_id
        foreign key (tenant_id)
            references tenants(id)
);