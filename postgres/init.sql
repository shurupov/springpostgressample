
create table if not exists sb_user
(
  id serial not null,
  first varchar(255),
  last varchar(255)
)
;

alter table sb_user owner to springboot
;

CREATE TABLE public.sb_user
(
  id integer DEFAULT nextval('sb_user_id_seq'::regclass) NOT NULL,
  first varchar(255),
  last varchar(255)
);
INSERT INTO public.sb_user (id, first, last) VALUES (1, 'Tom', 'Smith');
INSERT INTO public.sb_user (id, first, last) VALUES (2, 'Jack', 'Bridge');
INSERT INTO public.sb_user (id, first, last) VALUES (3, 'Nicole', 'Bash');