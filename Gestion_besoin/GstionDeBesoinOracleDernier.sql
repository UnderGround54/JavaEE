drop table CATEGORIE cascade constraints;

drop table DEMANDE cascade constraints;

drop table DIRECTION cascade constraints;

drop table LIGNE_DEMANDE cascade constraints;

drop table LIGNE_TRANSFERT cascade constraints;

drop table ARTICLE cascade constraints;

drop table SERVICE_AGENCE cascade constraints;

drop table STATUT cascade constraints;

drop table TRANSFERT cascade constraints;

drop table UTILISATEUR cascade constraints;

create table CATEGORIE 
(
   categorieID        INTEGER              not null,
   libelleCategorie   VARCHAR2(254),
   constraint PK_CATEGORIE primary key (categorieID)
);

create table DIRECTION 
(
   directionID        INTEGER              not null,
   libelleDirection   VARCHAR2(254),
   abreviationDirection VARCHAR2(254),
   constraint PK_DIRECTION primary key (directionID)
);

create table ARTICLE 
(
   articleID          INTEGER              not null,
   categorieID        INTEGER              not null,
   nomArticle        VARCHAR2(254),
   stock              INTEGER,
   etat               INTEGER,
   codeArticle       VARCHAR2(254),
   constraint PK_ARTICLE primary key (articleID),
   constraint FK_ARTICLE_CATEGORI foreign key (categorieID)
         references CATEGORIE (categorieID)
         on delete cascade
);

create table SERVICE_AGENCE 
(
   srvc_AgcID         INTEGER              not null,
   directionID        INTEGER              not null,
   nomSrvc_Agc        VARCHAR2(254),
   typeSrvc_Agc       VARCHAR2(254),
   lieuSrvc_Agc       VARCHAR2(254),
   abreviationSrvc_Agc VARCHAR2(254),
   codeSrvc_Agc       VARCHAR2(254),
   constraint PK_SERVICE_AGENCE primary key (srvc_AgcID),
   constraint FK_SERVICEA_DIRECTIO foreign key (directionID)
         references DIRECTION (directionID)
         on delete cascade
);

create table STATUT 
(
   statutID           INTEGER              not null,
   libelleStatut      VARCHAR2(254),
   constraint PK_STATUT primary key (statutID)
);

create table UTILISATEUR 
(
   userID             INTEGER              not null,
   srvc_AgcID         INTEGER              not null,
   NomUser            VARCHAR2(254),
   PrenomUser         VARCHAR2(254),
   LoginUser          VARCHAR2(254),
   AdresseUser        VARCHAR2(254),
   motDePass          VARCHAR2(254),
   posteUser          VARCHAR2(254),
   droitUser          INTEGER,
   constraint PK_UTILISATEUR primary key (userID),
   constraint FK_UTILISAT_SERVICEA foreign key (srvc_AgcID)
         references SERVICE_AGENCE (srvc_AgcID)
         on delete cascade
);
create table DEMANDE 
(
   demandeID          INTEGER              not null,
   statutID           INTEGER              not null,
   userID             INTEGER              not null,
   srvc_AgcID         INTEGER              not null,
   dateDemande        DATE,
   numDemande         VARCHAR2(254),
   constraint PK_DEMANDE primary key (demandeID),
   constraint FK_DEMANDE_STATUT foreign key (statutID)
         references STATUT (statutID)
         on delete cascade,
   constraint FK_DEMANDE_UTILISATEUR foreign key (userID)
         references UTILISATEUR (userID)
         on delete cascade,
   constraint FK_DEMANDE_SERVICEA foreign key (srvc_AgcID)
         references SERVICE_AGENCE (srvc_AgcID)
         on delete cascade
);

create table LIGNE_DEMANDE 
(
	ligneDemandeID     INTEGER              not null,
   demandeID          INTEGER              not null,
   categorieID        INTEGER              not null,
   qteArticleDemande         INTEGER,
   commentaireDemande VARCHAR2(254),
   constraint PK_LIGNE_DEMANDE primary key (ligneDemandeID),
   constraint FK_LIGNE_DEMANDE_CATEGORI foreign key (categorieID)
         references CATEGORIE (categorieID)
         on delete cascade,
    constraint FK_LIGNE_DEMANDE_DEMANDE foreign key (demandeID)
         references DEMANDE (demandeID)
         on delete cascade
);

create table TRANSFERT 
(
   transfertID        INTEGER             not null,
   demandeID          INTEGER,
   srvc_AgcID         INTEGER              not null,
   statutID           INTEGER              not null,
   userID             INTEGER              not null,
   numTransfert        VARCHAR2(254),
   dateTransfert       DATE,
   constraint PK_TRANSFERT primary key (transfertID),
   constraint FK_TRANSFER_DEMANDE foreign key (demandeID)
         references DEMANDE (demandeID)
         on delete cascade,
   constraint FK_TRANSFER_STATUT foreign key (statutID)
         references STATUT (statutID)
         on delete cascade,
   constraint FK_TRANSFER_SERVICEA foreign key (srvc_AgcID)
         references SERVICE_AGENCE (srvc_AgcID)
         on delete cascade,
    constraint FK_TRANSFER_UTILISATEUR foreign key (userID)
         references UTILISATEUR (userID)
         on delete cascade
);
create table LIGNE_TRANSFERT 
(
   ligneTransfertID   INTEGER              not null,
   transfertID        INTEGER             not null,
   articleID         INTEGER              not null,
   
   qteArticleTransfert        INTEGER,
   commentaireTransfert VARCHAR2(254),
   constraint PK_LIGNE_TRANSFERT primary key (ligneTransfertID),
   constraint FK_LIGNE_DEMANDE_ARTICLE foreign key (articleID)
         references ARTICLE (articleID)
         on delete cascade,
    constraint FK_LIGNE_TRANSFERT_TRANSFERT foreign key (transfertID)
         references TRANSFERT (transfertID)
         on delete cascade
);

