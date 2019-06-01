PGDMP     '    -    
            w            db_pendiente    9.6.3    9.6.3 .    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           1262    98314    db_pendiente    DATABASE     �   CREATE DATABASE db_pendiente WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE db_pendiente;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12387    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    98384    donacion    TABLE     �   CREATE TABLE donacion (
    id_donacion integer NOT NULL,
    cantidad integer,
    mensaje text,
    monto_total numeric,
    id_producto integer,
    id_usuario integer
);
    DROP TABLE public.donacion;
       public         postgres    false    3            �            1259    98382    donacion_id_donacion_seq    SEQUENCE     z   CREATE SEQUENCE donacion_id_donacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.donacion_id_donacion_seq;
       public       postgres    false    3    194            �           0    0    donacion_id_donacion_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE donacion_id_donacion_seq OWNED BY donacion.id_donacion;
            public       postgres    false    193            �            1259    98317    empresa    TABLE     u   CREATE TABLE empresa (
    id_empresa integer NOT NULL,
    nombre character varying,
    rubro character varying
);
    DROP TABLE public.empresa;
       public         postgres    false    3            �            1259    98315    empresa_id_empresa_seq    SEQUENCE     x   CREATE SEQUENCE empresa_id_empresa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.empresa_id_empresa_seq;
       public       postgres    false    3    186            �           0    0    empresa_id_empresa_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE empresa_id_empresa_seq OWNED BY empresa.id_empresa;
            public       postgres    false    185            �            1259    98355    producto    TABLE     �   CREATE TABLE producto (
    id_producto integer NOT NULL,
    nombre character varying,
    precio numeric,
    imagen text,
    stock text,
    id_empresa integer
);
    DROP TABLE public.producto;
       public         postgres    false    3            �            1259    98353    producto_id_producto_seq    SEQUENCE     z   CREATE SEQUENCE producto_id_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.producto_id_producto_seq;
       public       postgres    false    3    190            �           0    0    producto_id_producto_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE producto_id_producto_seq OWNED BY producto.id_producto;
            public       postgres    false    189            �            1259    98339    sede    TABLE     �   CREATE TABLE sede (
    id_sede integer NOT NULL,
    nombre character varying,
    latitud text,
    longitud text,
    direccion text,
    id_empresa integer
);
    DROP TABLE public.sede;
       public         postgres    false    3            �            1259    98337    sede_id_sede_seq    SEQUENCE     r   CREATE SEQUENCE sede_id_sede_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.sede_id_sede_seq;
       public       postgres    false    3    188            �           0    0    sede_id_sede_seq    SEQUENCE OWNED BY     7   ALTER SEQUENCE sede_id_sede_seq OWNED BY sede.id_sede;
            public       postgres    false    187            �            1259    98371    usuario    TABLE     �   CREATE TABLE usuario (
    id_usuario integer NOT NULL,
    nombre text,
    apellido text,
    dni text,
    tipo_tarjeta text,
    num_tarjeta text
);
    DROP TABLE public.usuario;
       public         postgres    false    3            �            1259    98369    usuario_id_usuario_seq    SEQUENCE     x   CREATE SEQUENCE usuario_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.usuario_id_usuario_seq;
       public       postgres    false    3    192            �           0    0    usuario_id_usuario_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE usuario_id_usuario_seq OWNED BY usuario.id_usuario;
            public       postgres    false    191            �           2604    98387    donacion id_donacion    DEFAULT     n   ALTER TABLE ONLY donacion ALTER COLUMN id_donacion SET DEFAULT nextval('donacion_id_donacion_seq'::regclass);
 C   ALTER TABLE public.donacion ALTER COLUMN id_donacion DROP DEFAULT;
       public       postgres    false    194    193    194            �           2604    98320    empresa id_empresa    DEFAULT     j   ALTER TABLE ONLY empresa ALTER COLUMN id_empresa SET DEFAULT nextval('empresa_id_empresa_seq'::regclass);
 A   ALTER TABLE public.empresa ALTER COLUMN id_empresa DROP DEFAULT;
       public       postgres    false    185    186    186            �           2604    98358    producto id_producto    DEFAULT     n   ALTER TABLE ONLY producto ALTER COLUMN id_producto SET DEFAULT nextval('producto_id_producto_seq'::regclass);
 C   ALTER TABLE public.producto ALTER COLUMN id_producto DROP DEFAULT;
       public       postgres    false    189    190    190            �           2604    98342    sede id_sede    DEFAULT     ^   ALTER TABLE ONLY sede ALTER COLUMN id_sede SET DEFAULT nextval('sede_id_sede_seq'::regclass);
 ;   ALTER TABLE public.sede ALTER COLUMN id_sede DROP DEFAULT;
       public       postgres    false    187    188    188            �           2604    98374    usuario id_usuario    DEFAULT     j   ALTER TABLE ONLY usuario ALTER COLUMN id_usuario SET DEFAULT nextval('usuario_id_usuario_seq'::regclass);
 A   ALTER TABLE public.usuario ALTER COLUMN id_usuario DROP DEFAULT;
       public       postgres    false    192    191    192                      0    98384    donacion 
   TABLE DATA               a   COPY donacion (id_donacion, cantidad, mensaje, monto_total, id_producto, id_usuario) FROM stdin;
    public       postgres    false    194   �0       �           0    0    donacion_id_donacion_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('donacion_id_donacion_seq', 1, false);
            public       postgres    false    193            w          0    98317    empresa 
   TABLE DATA               5   COPY empresa (id_empresa, nombre, rubro) FROM stdin;
    public       postgres    false    186   �0       �           0    0    empresa_id_empresa_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('empresa_id_empresa_seq', 1, false);
            public       postgres    false    185            {          0    98355    producto 
   TABLE DATA               S   COPY producto (id_producto, nombre, precio, imagen, stock, id_empresa) FROM stdin;
    public       postgres    false    190   �0       �           0    0    producto_id_producto_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('producto_id_producto_seq', 1, false);
            public       postgres    false    189            y          0    98339    sede 
   TABLE DATA               R   COPY sede (id_sede, nombre, latitud, longitud, direccion, id_empresa) FROM stdin;
    public       postgres    false    188   1       �           0    0    sede_id_sede_seq    SEQUENCE SET     8   SELECT pg_catalog.setval('sede_id_sede_seq', 1, false);
            public       postgres    false    187            }          0    98371    usuario 
   TABLE DATA               X   COPY usuario (id_usuario, nombre, apellido, dni, tipo_tarjeta, num_tarjeta) FROM stdin;
    public       postgres    false    192   1       �           0    0    usuario_id_usuario_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('usuario_id_usuario_seq', 1, false);
            public       postgres    false    191            �           2606    98392    donacion donacion_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY donacion
    ADD CONSTRAINT donacion_pkey PRIMARY KEY (id_donacion);
 @   ALTER TABLE ONLY public.donacion DROP CONSTRAINT donacion_pkey;
       public         postgres    false    194    194            �           2606    98325    empresa empresa_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (id_empresa);
 >   ALTER TABLE ONLY public.empresa DROP CONSTRAINT empresa_pkey;
       public         postgres    false    186    186            �           2606    98363    producto producto_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);
 @   ALTER TABLE ONLY public.producto DROP CONSTRAINT producto_pkey;
       public         postgres    false    190    190            �           2606    98347    sede sede_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY sede
    ADD CONSTRAINT sede_pkey PRIMARY KEY (id_sede);
 8   ALTER TABLE ONLY public.sede DROP CONSTRAINT sede_pkey;
       public         postgres    false    188    188            �           2606    98379    usuario usuario_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public         postgres    false    192    192            �           2606    98393 !   donacion Ref_donacion_to_producto    FK CONSTRAINT     �   ALTER TABLE ONLY donacion
    ADD CONSTRAINT "Ref_donacion_to_producto" FOREIGN KEY (id_producto) REFERENCES producto(id_producto);
 M   ALTER TABLE ONLY public.donacion DROP CONSTRAINT "Ref_donacion_to_producto";
       public       postgres    false    2040    194    190                        2606    98398     donacion Ref_donacion_to_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY donacion
    ADD CONSTRAINT "Ref_donacion_to_usuario" FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
 L   ALTER TABLE ONLY public.donacion DROP CONSTRAINT "Ref_donacion_to_usuario";
       public       postgres    false    192    2042    194            �           2606    98364     producto Ref_producto_to_empresa    FK CONSTRAINT     �   ALTER TABLE ONLY producto
    ADD CONSTRAINT "Ref_producto_to_empresa" FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);
 L   ALTER TABLE ONLY public.producto DROP CONSTRAINT "Ref_producto_to_empresa";
       public       postgres    false    190    2036    186            �           2606    98348    sede Ref_sede_to_empresa    FK CONSTRAINT     x   ALTER TABLE ONLY sede
    ADD CONSTRAINT "Ref_sede_to_empresa" FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa);
 D   ALTER TABLE ONLY public.sede DROP CONSTRAINT "Ref_sede_to_empresa";
       public       postgres    false    2036    186    188                  x������ � �      w      x������ � �      {      x������ � �      y      x������ � �      }      x������ � �     