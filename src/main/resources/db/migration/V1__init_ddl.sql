-- partner
create table partners
(
    id            bigint auto_increment primary key comment 'ID',
    partner_token varchar(255) not null comment 'partner_token',
    partner_name  varchar(255) not null comment '파트너명',
    business_no   varchar(255) not null comment '사업자등록번호',
    email         varchar(255) not null comment 'email',
    status        varchar(30)  not null default 'ENABLE' comment '상태',
    created_at    datetime(6) not null comment '생성 일시',
    updated_at    datetime(6) null comment '수정 일시'
) comment 'partners' charset = utf8mb4;

create
index partner_idx01 on partners (partner_token);

create
index partner_idx02 on partners (created_at);

create
index partner_idx03 on partners (updated_at);

-- item
create table items
(
    id         bigint auto_increment primary key comment 'ID',
    item_token varchar(255) not null comment 'item_token',
    partner_id bigint       not null comment '파트너 ID',
    item_name  varchar(255) not null comment '상품명',
    item_price int(11) not null comment '상품 가격',
    status     varchar(30)  not null default 'PREPARE' comment '상태',
    deleted_at datetime(6) null comment '삭제 일시',
    created_at datetime(6) not null comment '생성 일시',
    updated_at datetime(6) null comment '수정 일시'
) comment 'items' charset = utf8mb4;

create
index item_idx01 on items (item_token);

create
index item_idx02 on items (partner_id);

create
index item_idx03 on items (created_at);

create
index item_idx04 on items (updated_at);

create
index item_idx05 on items (deleted_at);


-- item_option_group
create table item_option_groups
(
    id                     bigint auto_increment primary key comment 'ID',
    item_id                bigint      not null comment '상품 ID',
    ordering               tinyint(3) not null comment '정렬순서',
    item_option_group_name varchar(30) not null comment '옵션그룹명 (색상, 사이즈 등)',
    created_at             datetime(6) not null comment '생성 일시',
    updated_at             datetime(6) null comment '수정 일시'
) comment 'item_option_groups' charset = utf8mb4;

create
index item_option_group_idx01 on item_option_groups (item_id);

create
index item_option_group_idx02 on item_option_groups (created_at);

create
index item_option_group_idx03 on item_option_groups (updated_at);


-- item_option
create table item_options
(
    id                   bigint auto_increment primary key comment 'ID',
    item_option_group_id bigint      not null comment '상품 옵션 그룹 ID',
    ordering             tinyint(3) not null comment '정렬순서',
    item_option_name     varchar(30) not null comment '옵션명 (빨강, 파랑, XL, L)',
    item_option_price    int(11) not null comment '상품 옵션 가격',
    created_at           datetime(6) not null comment '생성 일시',
    updated_at           datetime(6) null comment '수정 일시'
) comment 'item_options' charset = utf8mb4;

create
index item_option_idx01 on item_options (item_option_group_id);

create
index item_option_idx02 on item_options (created_at);

create
index item_option_idx03 on item_options (updated_at);
