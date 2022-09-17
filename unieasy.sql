--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16583)
-- Name: appello; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.appello (
    codice_app character varying(8) NOT NULL,
    data timestamp without time zone NOT NULL,
    aula character varying(20),
    tipo character varying(20),
    cod_materia character varying(8) NOT NULL
);


ALTER TABLE public.appello OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16540)
-- Name: corso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.corso (
    codice character varying(4) NOT NULL,
    nome character varying(40)
);


ALTER TABLE public.corso OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16428)
-- Name: docente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.docente (
    username character varying(10) NOT NULL,
    password character varying(15) NOT NULL,
    nome character varying(25) NOT NULL,
    cognome character varying(25) NOT NULL,
    email character varying(35),
    telefono character(10)
);


ALTER TABLE public.docente OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16570)
-- Name: esame; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.esame (
    cod_materia character varying(8) NOT NULL,
    mat_studente character varying(6) NOT NULL,
    data timestamp without time zone,
    voto character varying(3)
);


ALTER TABLE public.esame OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16545)
-- Name: materia; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.materia (
    codice_mat character varying(8) NOT NULL,
    nome character varying(25) NOT NULL,
    cfu character varying(2) NOT NULL,
    user_docente character varying(10) NOT NULL,
    cod_corso character varying(4) NOT NULL
);


ALTER TABLE public.materia OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16594)
-- Name: prenotazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prenotazione (
    cod_appello character varying(8) NOT NULL,
    mat_studente character varying(6) NOT NULL,
    data timestamp without time zone
);


ALTER TABLE public.prenotazione OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16560)
-- Name: studente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.studente (
    matricola character varying(6) NOT NULL,
    password character varying(15) NOT NULL,
    nome character varying(25) NOT NULL,
    cognome character varying(25) NOT NULL,
    email character varying(35) NOT NULL,
    telefono character varying(10) NOT NULL,
    cod_corso character varying(4) NOT NULL
);


ALTER TABLE public.studente OWNER TO postgres;

--
-- TOC entry 3196 (class 2606 OID 16587)
-- Name: appello appello_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appello
    ADD CONSTRAINT appello_pkey PRIMARY KEY (codice_app);


--
-- TOC entry 3190 (class 2606 OID 16544)
-- Name: corso corso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.corso
    ADD CONSTRAINT corso_pkey PRIMARY KEY (codice);


--
-- TOC entry 3188 (class 2606 OID 16432)
-- Name: docente docente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.docente
    ADD CONSTRAINT docente_pkey PRIMARY KEY (username);


--
-- TOC entry 3192 (class 2606 OID 16549)
-- Name: materia materia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT materia_pkey PRIMARY KEY (codice_mat);


--
-- TOC entry 3194 (class 2606 OID 16564)
-- Name: studente studente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studente
    ADD CONSTRAINT studente_pkey PRIMARY KEY (matricola);


--
-- TOC entry 3203 (class 2606 OID 16597)
-- Name: prenotazione fk_appello; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT fk_appello FOREIGN KEY (cod_appello) REFERENCES public.appello(codice_app);


--
-- TOC entry 3198 (class 2606 OID 16555)
-- Name: materia fk_corso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT fk_corso FOREIGN KEY (cod_corso) REFERENCES public.corso(codice);


--
-- TOC entry 3199 (class 2606 OID 16565)
-- Name: studente fk_corso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.studente
    ADD CONSTRAINT fk_corso FOREIGN KEY (cod_corso) REFERENCES public.corso(codice);


--
-- TOC entry 3197 (class 2606 OID 16550)
-- Name: materia fk_docente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.materia
    ADD CONSTRAINT fk_docente FOREIGN KEY (user_docente) REFERENCES public.docente(username);


--
-- TOC entry 3200 (class 2606 OID 16573)
-- Name: esame fk_materia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.esame
    ADD CONSTRAINT fk_materia FOREIGN KEY (cod_materia) REFERENCES public.materia(codice_mat);


--
-- TOC entry 3202 (class 2606 OID 16588)
-- Name: appello fk_materia; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.appello
    ADD CONSTRAINT fk_materia FOREIGN KEY (cod_materia) REFERENCES public.materia(codice_mat);


--
-- TOC entry 3201 (class 2606 OID 16578)
-- Name: esame fk_studente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.esame
    ADD CONSTRAINT fk_studente FOREIGN KEY (mat_studente) REFERENCES public.studente(matricola);


--
-- TOC entry 3204 (class 2606 OID 16602)
-- Name: prenotazione fk_studente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prenotazione
    ADD CONSTRAINT fk_studente FOREIGN KEY (mat_studente) REFERENCES public.studente(matricola);


INSERT INTO public.docente(
	username, password, nome, cognome, email, telefono)
	VALUES ('MarioR', 'pass4567', 'Mario', 'Rossi', 'mariorossi@euniversity.it', '3201122822'),
	('FrancG', 'frgl1111', 'Francesca', 'Guzzi', 'francescaguzzi@euniversity.it', '3295685412'),
	('GiovM', 'ggmm1111', 'Giovanni', 'Mei', 'giovanni@euniversity.it', '3215698001'),
	('LucaT', 'luct1122', 'Luca', 'Tei', 'lucatei@euniversity.it', '3209966331');
	
INSERT INTO public.corso(
	codice, nome)
	VALUES ('1122', 'Ingegneria Meccanica'),
	('5478', 'Ingegneria Informatica');
	
INSERT INTO public.studente(
	matricola, password, nome, cognome, email, telefono, cod_corso)
	VALUES ('147283', 'pass1234', 'Tiziana', 'Parisi', 'tizianaparisi@euniversity.it', '3202033111', '5478'),
	('142536', 'pass1234', 'Lucia', 'Dello', 'luciad@euniversity.it', '3205588999', '5478'),
	('145967', 'pass1234', 'Biagio', 'Cacci', 'biagioc@euniversity.it', '3203325455', '1122'),
	('148620', 'pass1234', 'Valerio', 'Selvi', 'valerios@euniversity.it', '3256374894', '1122'),
	('149630', 'pass1234', 'Giuseppe', 'Sella', 'giuseppese@euniversity.it', '3339610254', '5478');
	
INSERT INTO public.materia(
	codice_mat, nome, cfu, user_docente, cod_corso)
	VALUES ('01455671', 'Reti di Calcolatori', '6', 'LucaT', '5478'),
	('03232106', 'Analisi I', '12', 'MarioR', '5478'),
	('54963216', 'Analisi II', '9', 'MarioR', '5478'),
	('65102389', 'Comunicazioni Elettriche', '6', 'GiovM', '5478'),
	('8596147', 'Controlli Automatici', '9', 'FrancG', '5478'),
	('23632106', 'Geometria', '9', 'MarioR', '1122'),
	('25963144', 'Fisica Tecnica', '6', 'LucaT', '1122'),
	('89632075', 'Fluidodinamica', '6', 'GiovM', '1122'),
	('96412587', 'Energetica', '9', 'GiovM', '1122'),
	('65423017', 'Elettrotecnica', '9', 'LucaT', '1122'),
	('52463311', 'Circuiti Elettrici', '6', 'GiovM', '5478'),
	('13220542', 'Ing. del Software', '6', 'LucaT', '5478'),
	('21546398', 'Applicazioni Web', '6', 'GiovM', '5478');
	

INSERT INTO public.esame(
	cod_materia, mat_studente, data, voto)
	VALUES ('03232106', '147283', '2022-02-02 00:00:00', '25'),
	('01455671', '147283', '2022-02-02 00:00:00', '28'),
	('52463311', '147283', '2022-02-15 00:00:00', '24'),
	('13220542', '147283', '2022-09-04 00:00:00', '21'),
	('03232106', '142536', '2022-06-14 00:00:00', '22'),
	('65102389', '142536', '2022-02-12 00:00:00', '20'),
	('23632106', '145967', '2022-02-02 00:00:00', '19'),
	('23632106', '145967', '2022-02-02 00:00:00', '19');
	
INSERT INTO public.appello(
	codice_app, data, aula, tipo, cod_materia)
	VALUES ('60219687', '2022-01-25 16:30:00', 'E22', 'Orale', '8596147'),
	('15963245', '2022-02-17 18:00:00', 'L12', 'Scritto', '8596147'),
	('96385214', '2022-05-14 18:30:00', 'L7', 'Scritto', '01455671'),
	('13975286', '2022-02-10 15:30:00', 'F1', 'Scritto', '21546398'),
	('96458721', '2022-02-22 10:30:00', 'B5', 'Orale', '65423017'),
	('54123698', '2022-02-26 11:30:00', 'B3', 'Scritto', '96412587'),
	('88779956', '2022-06-11 16:30:00', 'M12', 'Scritto', '03232106');


INSERT INTO public.prenotazione(
	cod_appello, mat_studente, data)
	VALUES ('60219687', '147283', '2022-09-12 00:00:00'),
	('15963245', '147283', '2022-09-12 00:00:00'),
	('60219687', '142536', '2022-02-12 00:00:00'),
	('96385214', '142536', '2022-02-12 00:00:00'),
	('13975286', '142536', '2022-02-12 00:00:00'),
	('96458721', '145967', '2022-02-12 00:00:00'),
	('54123698', '145967', '2022-02-12 00:00:00'),
	('88779956', '148620', '2022-02-12 00:00:00');
	

--
-- PostgreSQL database dump complete
--

