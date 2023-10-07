alter table doctors add last_name VARCHAR(100) after name;

alter table doctors add phone VARCHAR(100) after last_name;

alter table doctors add license_number VARCHAR(50) after speciality;