create or replace PACKAGE BODY        "PK_EXTRATOR_PGTO" IS
    ERRO_DOC_EXISTE EXCEPTION;

-- Procedimento para exclusao de dados na tabela de historicos.
PROCEDURE PR_EXCLUI_HISTORICO_PGTO (
     NUM_PGTO IN NUMBER
) IS
    BEGIN
        DELETE
        FROM
            H_RETIFICACAO_PGS
        WHERE
            REPG_PGTO_NR = NUM_PGTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela H_RETIFICACAO_PGS.');

            RAISE;
        END;
    END PR_EXCLUI_HISTORICO_PGTO;


-- Procedimento para exclusao de dados na tabela de restituicao.
PROCEDURE PR_EXCLUI_RESTIT_PGTO (
     NUM_PGTO IN     NUMBER
) IS
    BEGIN
        DELETE
        FROM
            CR_RESTIT_PGTOS
        WHERE
            CRPG_PGTO_NR = NUM_PGTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela CR_RESTIT_PGTOS.');

            RAISE;
        END;
    END PR_EXCLUI_RESTIT_PGTO;


-- Procedimento para exclusao de dados na tabela de bloqueios.
PROCEDURE PR_EXCLUI_BLOQUEIOS_PGTO (
     NUM_PGTO IN     NUMBER
) IS
    BEGIN
        DELETE
        FROM
            BLOQUEIO_PGTO_PROCS
        WHERE
            BLPP_PGTO_NR = NUM_PGTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela BLOQUEIO_PGTO_PROCS.');

            RAISE;
        END;
    END PR_EXCLUI_BLOQUEIOS_PGTO;


-- Procedimento para exclusao de dados na tabela de fracoes
PROCEDURE PR_EXCLUI_VALOR_RECEITAS (
     NUM_DOCUMENTO IN     NUMBER
) IS
    BEGIN
        DELETE
        FROM
            VALOR_RECEITAS
        WHERE
            VLRC_NR_ID = NUM_DOCUMENTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela VALOR_RECEITAS.');

            RAISE;
        END;
    END PR_EXCLUI_VALOR_RECEITAS;


-- Procedimento para exclusao de dados na tabela de complementos.
PROCEDURE PR_EXCLUI_COMPLEMENTO (
     NUM_INCONSISTENCIA IN     NUMBER
    ,NUM_DOCUMENTO      IN     NUMBER
) IS
    BEGIN
        DELETE
        FROM
            DOC_ARREC_COMPLEMS
        WHERE
            DARC_PGTO_NR_ID_AR = NUM_DOCUMENTO
            OR DARC_DOVR_NR_SQ = NUM_INCONSISTENCIA;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela DOC_ARREC_COMPLEMS.');

            RAISE;
        END;
    END PR_EXCLUI_COMPLEMENTO;


-- Procedimento para exclusao de dados na tabela de desmembramentos.
PROCEDURE PR_EXCLUI_DESMEMBRAMENTO (
     NUM_PAGAMENTO IN     NUMBER
    ,NUM_DOCUMENTO IN     NUMBER
) IS
    IDENTIFICADOR NUMBER;

    BEGIN
        IF NUM_DOCUMENTO IS NOT NULL THEN
            IDENTIFICADOR := NUM_DOCUMENTO;
        ELSE
            IDENTIFICADOR := NUM_PAGAMENTO;
        END IF;

        PR_EXCLUI_VALOR_RECEITAS(IDENTIFICADOR);

        DELETE
        FROM
            DOC_ARREC_DESMEMBS
        WHERE
            DARD_PGTO_NR_ID_AR = NUM_DOCUMENTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela DOC_ARREC_DESMEMBS.');

            RAISE;
        END;
    END PR_EXCLUI_DESMEMBRAMENTO;


-- Procedimento para exclusao de dados na tabela de classificacao.
PROCEDURE PR_EXCLUI_CLASSIFICACAO (
     NUM_PAGAMENTO IN     NUMBER
    ,NUM_DOCUMENTO IN     NUMBER
) IS
    BEGIN
        DELETE
        FROM
            CLASSIFICA_REC_DAS
        WHERE
            CRDA_NR_ID = NUM_DOCUMENTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela CLASSIFICA_REC_DAS.');

            RAISE;
        END;
    END PR_EXCLUI_CLASSIFICACAO;


-- Procedimento para exclusao de dados na tabela de inconsistencia.
PROCEDURE PR_EXCLUI_INCONSISTENCIA (
     NUM_DOCUMENTO IN     NUMBER
) IS
    BEGIN
        FOR I IN (
            SELECT *
            FROM
                DOC_AR_OBJETO_VERIFS
            WHERE
                DOVR_NR_EXTRA_BARRA = NUM_DOCUMENTO
                OR DOVR_NR_ID       = NUM_DOCUMENTO
        ) LOOP
            DELETE
            FROM
                DOC_AR_OBJETO_VERIFS
            WHERE
                DOVR_NR_SQ = I.DOVR_NR_SQ;

            IF (I.DOVR_NR_ID IS NOT NULL) THEN
                PR_EXCLUI_COMPLEMENTO(I.DOVR_NR_SQ, NULL);
            END IF;
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela DOC_AR_OBJETO_VERIFS.');

            RAISE;
        END;
    END PR_EXCLUI_INCONSISTENCIA;


-- Procedimento para exclusao de dados da tabela de operacoes contabeis
PROCEDURE PR_EXCLUI_OPER_CONTABIL (
     NUM_DOCUMENTO IN     NUMBER
) IS
    BEGIN
        DELETE
        FROM
            OPER_CONTABIL_PGTOS
        WHERE
            OCPG_NR_ID = NUM_DOCUMENTO;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela OPER_CONTABIL_PGTOS.');

            RAISE;
        END;
    END PR_EXCLUI_OPER_CONTABIL;


PROCEDURE PR_EXCLUI_REMESSA_LEVANTAMENTO (
     NUM_REMESSA NUMBER
) IS
    BEGIN
        DELETE
        FROM
            REMESSA_LEVANTAMENTOS
        WHERE
            RELE_NR_ID = NUM_REMESSA;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela REMESSA_LEVANTAMENTOS.');

            RAISE;
        END;
    END PR_EXCLUI_REMESSA_LEVANTAMENTO;


PROCEDURE PR_EXCLUI_DJE_REGISTRO (
      NUM_LEVANTAMENTO IN NUMBER
     ,SEQ_LEVANTAMENTO IN NUMBER
) IS
    BEGIN
        DELETE
        FROM
            LEVANTA_REC_DJES
        WHERE
            LRDJ_LREC_NR_LEVANTAMENTO = NUM_LEVANTAMENTO
            AND LRDJ_NR_SQ            = SEQ_LEVANTAMENTO;
    END PR_EXCLUI_DJE_REGISTRO;


PROCEDURE PR_EXCLUI_LEVANTA_REC_DJES (
     NUM_LEVANTAMENTO IN NUMBER
) IS
    CONTADOR NUMBER;

    BEGIN
        FOR D IN (
            SELECT *
            FROM
                LEVANTA_REC_DJES
            WHERE
                LRDJ_LREC_NR_LEVANTAMENTO = NUM_LEVANTAMENTO
        ) LOOP
            DELETE
            FROM
                LEVANTA_REC_DJES
            WHERE
                LRDJ_LREC_NR_LEVANTAMENTO = D.LRDJ_LREC_NR_LEVANTAMENTO
                AND LRDJ_NR_SQ = D.LRDJ_NR_SQ;
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela LEVANTA_REC_DJES.');

            RAISE;
        END;
    END PR_EXCLUI_LEVANTA_REC_DJES;


PROCEDURE PR_EXCLUI_LEVANTA_REGISTRO (
     NUM_LEVANTAMENTO IN NUMBER
) IS
    BEGIN
        DELETE
        FROM
            LEVANTAMENTO_RECS
        WHERE
            LREC_NR_LEVANTAMENTO = NUM_LEVANTAMENTO;
    END PR_EXCLUI_LEVANTA_REGISTRO;


PROCEDURE PR_EXCLUI_LEVANTAMENTO_RECS (
     NUM_CONTA IN NUMBER
) IS
    CONTADOR NUMBER;

    BEGIN
        FOR L IN (
            SELECT *
            FROM
                LEVANTAMENTO_RECS
            WHERE
                LREC_CDLV_NR_CONTA = NUM_CONTA
        ) LOOP
            PR_EXCLUI_REMESSA_LEVANTAMENTO(L.LREC_RELE_NR_ID);

            DELETE
            FROM
                LEVANTAMENTO_RECS
            WHERE
                LREC_NR_LEVANTAMENTO = L.LREC_NR_LEVANTAMENTO;

            PR_EXCLUI_LEVANTA_REC_DJES(L.LREC_NR_LEVANTAMENTO);
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela LEVANTAMENTO_RECS.');

            RAISE;
        END;
    END PR_EXCLUI_LEVANTAMENTO_RECS;


PROCEDURE PR_EXCLUI_CONTA_REGISTRO (
     NUM_CONTA IN NUMBER
) IS
    BEGIN
        DELETE
        FROM CONTA_DEP_LEVANTAS
        WHERE CDLV_NR_CONTA = NUM_CONTA;
    END PR_EXCLUI_CONTA_REGISTRO;


PROCEDURE PR_EXCLUI_CONTA_DEP_LEVANTAS (
     CNPJ_BASE  IN NUMBER
    ,CNPJ_ORDEM IN NUMBER
    ,CPF        IN NUMBER
) IS
    BEGIN
        FOR CD IN (
            SELECT *
            FROM
                CONTA_DEP_LEVANTAS
            WHERE
                (CDLV_ESTB_PJ_NR = CNPJ_BASE
                    AND CDLV_ESTB_NR = CNPJ_ORDEM)
                OR CDLV_PF_NR = CPF
        ) LOOP
            PR_EXCLUI_CONTA_REGISTRO(CD.CDLV_NR_CONTA);

            PR_EXCLUI_LEVANTAMENTO_RECS(CD.CDLV_NR_CONTA);
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela CONTA_DEP_LEVANTAS.');

            RAISE;
        END;
    END PR_EXCLUI_CONTA_DEP_LEVANTAS;


-- Procedimento para exclusao de dados na tabela de pagamentos.
PROCEDURE PR_EXCLUI_PGTO (
     PAGAMENTO IN PAGAMENTOS%ROWTYPE
) IS
    QTD_PGTO NUMBER;

    BEGIN
        QTD_PGTO := 0;

        SELECT
            COUNT(*) INTO QTD_PGTO
        FROM
            PAGAMENTOS
        WHERE
            PGTO_NR = PAGAMENTO.PGTO_NR;

        IF QTD_PGTO > 0 THEN
            PR_EXCLUI_INCONSISTENCIA(PAGAMENTO.PGTO_NR_ID_AR);

            PR_EXCLUI_CLASSIFICACAO(PAGAMENTO.PGTO_NR, PAGAMENTO.PGTO_NR_ID_AR);

            PR_EXCLUI_OPER_CONTABIL(PAGAMENTO.PGTO_NR_ID_AR);

            PR_EXCLUI_DESMEMBRAMENTO(PAGAMENTO.PGTO_NR, PAGAMENTO.PGTO_NR_ID_AR);

            PR_EXCLUI_COMPLEMENTO(NULL, PAGAMENTO.PGTO_NR_ID_AR);

            PR_EXCLUI_BLOQUEIOS_PGTO(PAGAMENTO.PGTO_NR);

            PR_EXCLUI_RESTIT_PGTO(PAGAMENTO.PGTO_NR);

            PR_EXCLUI_HISTORICO_PGTO(PAGAMENTO.PGTO_NR);

            DELETE
            FROM
                PAGAMENTOS
            WHERE
                PGTO_NR = PAGAMENTO.PGTO_NR;
        END IF; -- END de QTD_PGTO > 0

        COMMIT;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro excluindo registro na tabela PAGAMENTOS.');

            RAISE;
        END;
    END PR_EXCLUI_PGTO;


-- Procedimento para insercao na tabela de historico
PROCEDURE PR_INSERE_HISTORICO_PGTO (
     NUM_PGTO IN NUMBER
) IS
    BEGIN
        FOR H IN (
            SELECT
                 REPG_PGTO_NR
                ,REPG_DT_ARRECADACAO
                ,REPG_DT_REGISTRO
                ,REPG_UA
                ,REPG_IN_TP
                ,REPG_IN_CONTRIB_N_IDENT
                ,REPG_SITU_CD
                ,REPG_VL_TOTAL
                ,REPG_IN_UTIL_COBRANCA
                ,REPG_B_REC_CD1
                ,REPG_B_VL1
                ,REPG_IN_IMPEDE_ALOC
                ,REPG_IN_ORIGEM
                ,REPG_AGEN_AGTA_CD
                ,REPG_AGEN_CD
                ,REPG_IN_DESMEMBRADO
                ,REPG_B_VL_RLOCAL
                ,REPG_B_VL_GPORTE
                ,REPG_B_IN_INTERESSE
                ,REPG_B_IN_PASSIVO
                ,REPG_IN_ATUALIZACAO
                ,REPG_DT_ATUALIZACAO
                ,REPG_ESTB_PJ_NR
                ,REPG_ESTB_NR
                ,REPG_OEPG_CD
                ,REPG_DT_VENCIMENTO
                ,REPG_NR_PERIODO_APUR
                ,REPG_NR_PROCESSO
                ,REPG_MCAN_CD
                ,REPG_PC_REC_BRUT
                ,REPG_VL_REC_BRUT_ACUM
                ,REPG_NR_REFERENCIA
                ,REPG_PROC_NR
                ,REPG_NR_PARCELA
                ,REPG_RELE_NR_SQ
                ,REPG_PF_NR
                ,REPG_B_NR_BDA_SQ
                ,REPG_RPAP_NR_BDA
                ,REPG_B_DT_RECEPCAO
                ,REPG_DT_ENCERRA_PER_APU
                ,REPG_ALSP_CD
                ,REPG_MNDP_CD
                ,REPG_B_VL2
                ,REPG_B_VL3
                ,REPG_B_VL4
                ,REPG_B_VL5
                ,REPG_B_REC_CD2
                ,REPG_B_REC_CD3
                ,REPG_B_REC_CD4
                ,REPG_B_REC_CD5
                ,REPG_B_REC_DT1
                ,REPG_B_REC_DT2
                ,REPG_B_REC_DT3
                ,REPG_B_REC_DT4
                ,REPG_B_REC_DT5
                ,REPG_B_OEPG_DT
                ,REPG_B_SITU_DT
                ,REPG_B_AGEN_DT
                ,REPG_B_MCAN_DT
                ,REPG_B_MNDP_DT
                ,REPG_B_ALSP_DT
                ,REPG_B_UA_CD_ATUALIZA
                ,REPG_B_NR_TERMINAL
                ,REPG_B_PF_NR_ATUALIZA
                ,REPG_B_VL_RESTITUICAO
                ,REPG_B_VL_COMPENSACAO
                ,REPG_B_VL_ALOCADO_RL
                ,REPG_NR_CONTRIB_NIDENT
                ,REPG_IN_AUTORIZA_DEB_CC
                ,REPG_IN_MEIO_COLETA
                ,REPG_NR_PROTOCOL_DEB_CC
                ,REPG_NR_ID_DEPOSITO_CEF
                ,REPG_B_VL_SALDO_PRINCIPAL
                ,REPG_B_VL_SALDO_MULTA
                ,REPG_B_VL_SALDO_JUROS
                ,REPG_B_IN_REC_INVAL
                ,REPG_B_IN_DELECAO_LOGICA
                ,REPG_NR_DV_CONTRIB
                ,REPG_B_DT_REGISTRO_GPORTE
                ,REPG_NR_ID_AR
                ,REPG_IN_INCONSISTENCIA
                ,REPG_NR_REMESSA
                ,REPG_SEAR_TMAR_CD
                ,REPG_SEAR_TPR_CD
                ,REPG_SEAR_TSP_CD
                ,REPG_SEAR_TAUD_CD
                ,REPG_SEAR_TDA_CD
                ,REPG_TNR_CD
                ,REPG_DT_LIMITE_PGTO_DEB
                ,REPG_DT_RECEP_REG_EM_AG
                ,REPG_IN_MEIO_RECEP_INF
                ,REPG_NR_CTRL_RECEP_INF
                ,REPG_IN_COND_SIT_REG
                ,REPG_IN_MOD_ARREC
                ,REPG_DT_APROPRIACAO
                ,REPG_DT_PERIODO_APROPRIA
                ,REPG_QT_DOC_AGREGADO
                ,REPG_TIIC_CD
                ,REPG_DOVR_NR_SQ
                ,REPG_TSGI_CD
                ,REPG_TQRP_CD
                ,REPG_TBFR_CD
                ,REPG_TPSI_CD
                ,REPG_UA_DT_PGTO
                ,REPG_UA_CLASS_CD
                ,REPG_MUNIC_CLASS_CD
                ,REPG_B_CD_MUN_DOM_CONTRIB
                ,REPG_TIFP_CD
                ,REPG_B_IN_CLASS_PRINC
                ,REPG_B_IN_CLASS_MULTA
                ,REPG_B_IN_CLASS_JUROS
                ,REPG_IN_MEIO_RECEP_PG_RF
                ,REPG_NR_CTRL_RECEP_PG_RF
                ,REPG_DT_GER_REG_SIST_ORIG
                ,REPG_IN_ORIGEM_AR
                ,REPG_B_VL_DJE_SUNL
                ,REPG_B_VL_DJE_SDNL
                ,REPG_B_VL_DJE_SUTD
                ,REPG_B_VL_DJE_SDTD
                ,REPG_B_VL_DJE_VTDC
                ,REPG_NIT_NI
                ,REPG_PGTO_NR_ID_AR
                ,REPG_B_IP_TERMINAL
                ,REPG_IN_DARA
        --        ,REPG_B_IN_ALOCADO
            FROM
                H_RETIFICACAO_PGS@DL_POC08_EXTRATOR -- @DB_LINK_EXTRATOR
            WHERE
                REPG_PGTO_NR = NUM_PGTO
        ) LOOP -- LOOP na tabela H_RETIFICACAO_PGS (PRODUCAO)
            INSERT INTO H_RETIFICACAO_PGS (
                 REPG_PGTO_NR
                ,REPG_DT_ARRECADACAO
                ,REPG_DT_REGISTRO
                ,REPG_UA
                ,REPG_IN_TP
                ,REPG_IN_CONTRIB_N_IDENT
                ,REPG_SITU_CD
                ,REPG_VL_TOTAL
                ,REPG_IN_UTIL_COBRANCA
                ,REPG_B_REC_CD1
                ,REPG_B_VL1
                ,REPG_IN_IMPEDE_ALOC
                ,REPG_IN_ORIGEM
                ,REPG_AGEN_AGTA_CD
                ,REPG_AGEN_CD
                ,REPG_IN_DESMEMBRADO
                ,REPG_B_VL_RLOCAL
                ,REPG_B_VL_GPORTE
                ,REPG_B_IN_INTERESSE
                ,REPG_B_IN_PASSIVO
                ,REPG_IN_ATUALIZACAO
                ,REPG_DT_ATUALIZACAO
                ,REPG_ESTB_PJ_NR
                ,REPG_ESTB_NR
                ,REPG_OEPG_CD
                ,REPG_DT_VENCIMENTO
                ,REPG_NR_PERIODO_APUR
                ,REPG_NR_PROCESSO
                ,REPG_MCAN_CD
                ,REPG_PC_REC_BRUT
                ,REPG_VL_REC_BRUT_ACUM
                ,REPG_NR_REFERENCIA
                ,REPG_PROC_NR
                ,REPG_NR_PARCELA
                ,REPG_RELE_NR_SQ
                ,REPG_PF_NR
                ,REPG_B_NR_BDA_SQ
                ,REPG_RPAP_NR_BDA
                ,REPG_B_DT_RECEPCAO
                ,REPG_DT_ENCERRA_PER_APU
                ,REPG_ALSP_CD
                ,REPG_MNDP_CD
                ,REPG_B_VL2
                ,REPG_B_VL3
                ,REPG_B_VL4
                ,REPG_B_VL5
                ,REPG_B_REC_CD2
                ,REPG_B_REC_CD3
                ,REPG_B_REC_CD4
                ,REPG_B_REC_CD5
                ,REPG_B_REC_DT1
                ,REPG_B_REC_DT2
                ,REPG_B_REC_DT3
                ,REPG_B_REC_DT4
                ,REPG_B_REC_DT5
                ,REPG_B_OEPG_DT
                ,REPG_B_SITU_DT
                ,REPG_B_AGEN_DT
                ,REPG_B_MCAN_DT
                ,REPG_B_MNDP_DT
                ,REPG_B_ALSP_DT
                ,REPG_B_UA_CD_ATUALIZA
                ,REPG_B_NR_TERMINAL
                ,REPG_B_PF_NR_ATUALIZA
                ,REPG_B_VL_RESTITUICAO
                ,REPG_B_VL_COMPENSACAO
                ,REPG_B_VL_ALOCADO_RL
                ,REPG_NR_CONTRIB_NIDENT
                ,REPG_IN_AUTORIZA_DEB_CC
                ,REPG_IN_MEIO_COLETA
                ,REPG_NR_PROTOCOL_DEB_CC
                ,REPG_NR_ID_DEPOSITO_CEF
                ,REPG_B_VL_SALDO_PRINCIPAL
                ,REPG_B_VL_SALDO_MULTA
                ,REPG_B_VL_SALDO_JUROS
                ,REPG_B_IN_REC_INVAL
                ,REPG_B_IN_DELECAO_LOGICA
                ,REPG_NR_DV_CONTRIB
                ,REPG_B_DT_REGISTRO_GPORTE
                ,REPG_NR_ID_AR
                ,REPG_IN_INCONSISTENCIA
                ,REPG_NR_REMESSA
                ,REPG_SEAR_TMAR_CD
                ,REPG_SEAR_TPR_CD
                ,REPG_SEAR_TSP_CD
                ,REPG_SEAR_TAUD_CD
                ,REPG_SEAR_TDA_CD
                ,REPG_TNR_CD
                ,REPG_DT_LIMITE_PGTO_DEB
                ,REPG_DT_RECEP_REG_EM_AG
                ,REPG_IN_MEIO_RECEP_INF
                ,REPG_NR_CTRL_RECEP_INF
                ,REPG_IN_COND_SIT_REG
                ,REPG_IN_MOD_ARREC
                ,REPG_DT_APROPRIACAO
                ,REPG_DT_PERIODO_APROPRIA
                ,REPG_QT_DOC_AGREGADO
                ,REPG_TIIC_CD
                ,REPG_DOVR_NR_SQ
                ,REPG_TSGI_CD
                ,REPG_TQRP_CD
                ,REPG_TBFR_CD
                ,REPG_TPSI_CD
                ,REPG_UA_DT_PGTO
                ,REPG_UA_CLASS_CD
                ,REPG_MUNIC_CLASS_CD
                ,REPG_B_CD_MUN_DOM_CONTRIB
                ,REPG_TIFP_CD
                ,REPG_B_IN_CLASS_PRINC
                ,REPG_B_IN_CLASS_MULTA
                ,REPG_B_IN_CLASS_JUROS
                ,REPG_IN_MEIO_RECEP_PG_RF
                ,REPG_NR_CTRL_RECEP_PG_RF
                ,REPG_DT_GER_REG_SIST_ORIG
                ,REPG_IN_ORIGEM_AR
                ,REPG_B_VL_DJE_SUNL
                ,REPG_B_VL_DJE_SDNL
                ,REPG_B_VL_DJE_SUTD
                ,REPG_B_VL_DJE_SDTD
                ,REPG_B_VL_DJE_VTDC
                ,REPG_NIT_NI
                ,REPG_PGTO_NR_ID_AR
                ,REPG_B_IP_TERMINAL
                ,REPG_IN_DARA
            --    ,REPG_B_IN_ALOCADO
            ) VALUES (
                 H.REPG_PGTO_NR
                ,H.REPG_DT_ARRECADACAO
                ,H.REPG_DT_REGISTRO
                ,H.REPG_UA
                ,H.REPG_IN_TP
                ,H.REPG_IN_CONTRIB_N_IDENT
                ,H.REPG_SITU_CD
                ,H.REPG_VL_TOTAL
                ,H.REPG_IN_UTIL_COBRANCA
                ,H.REPG_B_REC_CD1
                ,H.REPG_B_VL1
                ,H.REPG_IN_IMPEDE_ALOC
                ,H.REPG_IN_ORIGEM
                ,H.REPG_AGEN_AGTA_CD
                ,H.REPG_AGEN_CD
                ,H.REPG_IN_DESMEMBRADO
                ,H.REPG_B_VL_RLOCAL
                ,H.REPG_B_VL_GPORTE
                ,H.REPG_B_IN_INTERESSE
                ,H.REPG_B_IN_PASSIVO
                ,H.REPG_IN_ATUALIZACAO
                ,H.REPG_DT_ATUALIZACAO
                ,H.REPG_ESTB_PJ_NR
                ,H.REPG_ESTB_NR
                ,H.REPG_OEPG_CD
                ,H.REPG_DT_VENCIMENTO
                ,H.REPG_NR_PERIODO_APUR
                ,H.REPG_NR_PROCESSO
                ,H.REPG_MCAN_CD
                ,H.REPG_PC_REC_BRUT
                ,H.REPG_VL_REC_BRUT_ACUM
                ,H.REPG_NR_REFERENCIA
                ,H.REPG_PROC_NR
                ,H.REPG_NR_PARCELA
                ,H.REPG_RELE_NR_SQ
                ,H.REPG_PF_NR
                ,H.REPG_B_NR_BDA_SQ
                ,H.REPG_RPAP_NR_BDA
                ,H.REPG_B_DT_RECEPCAO
                ,H.REPG_DT_ENCERRA_PER_APU
                ,H.REPG_ALSP_CD
                ,H.REPG_MNDP_CD
                ,H.REPG_B_VL2
                ,H.REPG_B_VL3
                ,H.REPG_B_VL4
                ,H.REPG_B_VL5
                ,H.REPG_B_REC_CD2
                ,H.REPG_B_REC_CD3
                ,H.REPG_B_REC_CD4
                ,H.REPG_B_REC_CD5
                ,H.REPG_B_REC_DT1
                ,H.REPG_B_REC_DT2
                ,H.REPG_B_REC_DT3
                ,H.REPG_B_REC_DT4
                ,H.REPG_B_REC_DT5
                ,H.REPG_B_OEPG_DT
                ,H.REPG_B_SITU_DT
                ,H.REPG_B_AGEN_DT
                ,H.REPG_B_MCAN_DT
                ,H.REPG_B_MNDP_DT
                ,H.REPG_B_ALSP_DT
                ,H.REPG_B_UA_CD_ATUALIZA
                ,H.REPG_B_NR_TERMINAL
                ,H.REPG_B_PF_NR_ATUALIZA
                ,H.REPG_B_VL_RESTITUICAO
                ,H.REPG_B_VL_COMPENSACAO
                ,H.REPG_B_VL_ALOCADO_RL
                ,H.REPG_NR_CONTRIB_NIDENT
                ,H.REPG_IN_AUTORIZA_DEB_CC
                ,H.REPG_IN_MEIO_COLETA
                ,H.REPG_NR_PROTOCOL_DEB_CC
                ,H.REPG_NR_ID_DEPOSITO_CEF
                ,H.REPG_B_VL_SALDO_PRINCIPAL
                ,H.REPG_B_VL_SALDO_MULTA
                ,H.REPG_B_VL_SALDO_JUROS
                ,H.REPG_B_IN_REC_INVAL
                ,H.REPG_B_IN_DELECAO_LOGICA
                ,H.REPG_NR_DV_CONTRIB
                ,H.REPG_B_DT_REGISTRO_GPORTE
                ,H.REPG_NR_ID_AR
                ,H.REPG_IN_INCONSISTENCIA
                ,H.REPG_NR_REMESSA
                ,H.REPG_SEAR_TMAR_CD
                ,H.REPG_SEAR_TPR_CD
                ,H.REPG_SEAR_TSP_CD
                ,H.REPG_SEAR_TAUD_CD
                ,H.REPG_SEAR_TDA_CD
                ,H.REPG_TNR_CD
                ,H.REPG_DT_LIMITE_PGTO_DEB
                ,H.REPG_DT_RECEP_REG_EM_AG
                ,H.REPG_IN_MEIO_RECEP_INF
                ,H.REPG_NR_CTRL_RECEP_INF
                ,H.REPG_IN_COND_SIT_REG
                ,H.REPG_IN_MOD_ARREC
                ,H.REPG_DT_APROPRIACAO
                ,H.REPG_DT_PERIODO_APROPRIA
                ,H.REPG_QT_DOC_AGREGADO
                ,H.REPG_TIIC_CD
                ,H.REPG_DOVR_NR_SQ
                ,H.REPG_TSGI_CD
                ,H.REPG_TQRP_CD
                ,H.REPG_TBFR_CD
                ,H.REPG_TPSI_CD
                ,H.REPG_UA_DT_PGTO
                ,H.REPG_UA_CLASS_CD
                ,H.REPG_MUNIC_CLASS_CD
                ,H.REPG_B_CD_MUN_DOM_CONTRIB
                ,H.REPG_TIFP_CD
                ,H.REPG_B_IN_CLASS_PRINC
                ,H.REPG_B_IN_CLASS_MULTA
                ,H.REPG_B_IN_CLASS_JUROS
                ,H.REPG_IN_MEIO_RECEP_PG_RF
                ,H.REPG_NR_CTRL_RECEP_PG_RF
                ,H.REPG_DT_GER_REG_SIST_ORIG
                ,H.REPG_IN_ORIGEM_AR
                ,H.REPG_B_VL_DJE_SUNL
                ,H.REPG_B_VL_DJE_SDNL
                ,H.REPG_B_VL_DJE_SUTD
                ,H.REPG_B_VL_DJE_SDTD
                ,H.REPG_B_VL_DJE_VTDC
                ,H.REPG_NIT_NI
                ,H.REPG_PGTO_NR_ID_AR
                ,H.REPG_B_IP_TERMINAL
                ,H.REPG_IN_DARA
            --    ,H.REPG_B_IN_ALOCADO
            );
        END LOOP; -- END LOOP na tabela H_RETIFICACAO_PGS (PRODUCAO)

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_PGTO || ' ja se encontra em PR_INSERE_HISTORICO_PGTO.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela H_RETIFICACAO_PGS.');

            RAISE;
        END;
    END PR_INSERE_HISTORICO_PGTO;


-- Procedimento para insercao na tabela de restituicao
PROCEDURE PR_INSERE_RESTIT_PGTO (
     NUM_PGTO IN NUMBER
) IS
    BEGIN
        FOR R IN (
            SELECT
                 CRPG_PGTO_NR
                ,CRPG_NR_SQ
                ,CRPG_DT
                ,CRPG_IN_SITUACAO
                ,CRPG_DT_REGISTRO
                ,CRPG_VL
                ,CRPG_B_IN_PASSIVO
                ,CRPG_B_UA_CD_ATUALIZA
                ,CRPG_B_NR_TERMINAL
                ,CRPG_B_PF_NR_ATUALIZA
                ,CRPG_B_UA_CD_CANC
                ,CRPG_B_PF_NR_CANC
                ,CRPG_B_NR_TERMINAL_CANC
                ,CRPG_B_DT_REGISTRO_GPORTE
                ,CRPG_B_DT_CANCELAMENTO
                ,CRPG_B_DT_CANC_GPORTE
                ,CRPG_PROC_NR
                ,CRPG_DT_RESGATE
                ,CRPG_DT_CONVER_RECEITA
            FROM
                CR_RESTIT_PGTOS@DL_POC08_EXTRATOR -- @DB_LINK_EXTRATOR
            WHERE
                CRPG_PGTO_NR = NUM_PGTO
        ) LOOP -- LOOP na tabela CR_RESTIT_PGTOS (PRODUCAO)
            INSERT INTO CR_RESTIT_PGTOS (
                 CRPG_PGTO_NR
                ,CRPG_NR_SQ
                ,CRPG_DT
                ,CRPG_IN_SITUACAO
                ,CRPG_DT_REGISTRO
                ,CRPG_VL
                ,CRPG_B_IN_PASSIVO
                ,CRPG_B_UA_CD_ATUALIZA
                ,CRPG_B_NR_TERMINAL
                ,CRPG_B_PF_NR_ATUALIZA
                ,CRPG_B_UA_CD_CANC
                ,CRPG_B_PF_NR_CANC
                ,CRPG_B_NR_TERMINAL_CANC
                ,CRPG_B_DT_REGISTRO_GPORTE
                ,CRPG_B_DT_CANCELAMENTO
                ,CRPG_B_DT_CANC_GPORTE
                ,CRPG_PROC_NR
                ,CRPG_DT_RESGATE
                ,CRPG_DT_CONVER_RECEITA
            ) VALUES (
                 R.CRPG_PGTO_NR
                ,R.CRPG_NR_SQ
                ,R.CRPG_DT
                ,R.CRPG_IN_SITUACAO
                ,R.CRPG_DT_REGISTRO
                ,R.CRPG_VL
                ,R.CRPG_B_IN_PASSIVO
                ,R.CRPG_B_UA_CD_ATUALIZA
                ,R.CRPG_B_NR_TERMINAL
                ,R.CRPG_B_PF_NR_ATUALIZA
                ,R.CRPG_B_UA_CD_CANC
                ,R.CRPG_B_PF_NR_CANC
                ,R.CRPG_B_NR_TERMINAL_CANC
                ,R.CRPG_B_DT_REGISTRO_GPORTE
                ,R.CRPG_B_DT_CANCELAMENTO
                ,R.CRPG_B_DT_CANC_GPORTE
                ,R.CRPG_PROC_NR
                ,R.CRPG_DT_RESGATE
                ,R.CRPG_DT_CONVER_RECEITA
            );
        END LOOP; -- END LOOP na tabela CR_RESTIT_PGTOS (PRODUCAO)

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_PGTO || ' ja se encontra em PR_INSERE_RESTIT_PGTO.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela CR_RESTIT_PGTOS.');

            RAISE;
        END;
    END PR_INSERE_RESTIT_PGTO;


-- Procedimento para insercao na tabela de bloqueios
PROCEDURE PR_INSERE_BLOQUEIOS_PGTO (
     NUM_PGTO IN NUMBER
) IS
    BEGIN
        FOR B IN (
            SELECT
                 BLPP_PGTO_NR
                ,BLPP_NR_SQ
                ,BLPP_DT_REGISTRO
                ,BLPP_VL_RESERVADO
                ,BLPP_DT_RESERVA
                ,BLPP_B_IN_DELECAO_LOGICA
                ,BLPP_B_UA_CD_ATUALIZA
                ,BLPP_B_NR_TERMINAL
                ,BLPP_B_PF_NR_ATUALIZA
                ,BLPP_B_RES_BL
                ,BLPP_VL_BLOQUEADO
                ,BLPP_DT_BLOQUEIO
                ,BLPP_PROC_NR
                ,BLPP_VLEP_PEDI_UA_CD
                ,BLPP_VLEP_PEDI_AN_EX
                ,BLPP_VLEP_PEDI_NR_SQ
                ,BLPP_VLEP_NR_SQ
                ,BLPP_B_PERDCOMP
                ,BLPP_DARD_PGTO_NR_ID_AR
                ,BLPP_DARD_NR_SQ
            FROM
                BLOQUEIO_PGTO_PROCS@DL_POC08_EXTRATOR -- @DB_LINK_EXTRATOR
            WHERE
                BLPP_PGTO_NR = NUM_PGTO
        ) LOOP -- LOOP na tabela BLOQUEIO_PGTO_PROCS (PRODUCAO)
            INSERT INTO BLOQUEIO_PGTO_PROCS (
                 BLPP_PGTO_NR
                ,BLPP_NR_SQ
                ,BLPP_DT_REGISTRO
                ,BLPP_VL_RESERVADO
                ,BLPP_DT_RESERVA
                ,BLPP_B_IN_DELECAO_LOGICA
                ,BLPP_B_UA_CD_ATUALIZA
                ,BLPP_B_NR_TERMINAL
                ,BLPP_B_PF_NR_ATUALIZA
                ,BLPP_B_RES_BL
                ,BLPP_VL_BLOQUEADO
                ,BLPP_DT_BLOQUEIO
                ,BLPP_PROC_NR
                ,BLPP_VLEP_PEDI_UA_CD
                ,BLPP_VLEP_PEDI_AN_EX
                ,BLPP_VLEP_PEDI_NR_SQ
                ,BLPP_VLEP_NR_SQ
                ,BLPP_B_PERDCOMP
                ,BLPP_DARD_PGTO_NR_ID_AR
                ,BLPP_DARD_NR_SQ
            ) VALUES (
                 B.BLPP_PGTO_NR
                ,B.BLPP_NR_SQ
                ,B.BLPP_DT_REGISTRO
                ,B.BLPP_VL_RESERVADO
                ,B.BLPP_DT_RESERVA
                ,B.BLPP_B_IN_DELECAO_LOGICA
                ,B.BLPP_B_UA_CD_ATUALIZA
                ,B.BLPP_B_NR_TERMINAL
                ,B.BLPP_B_PF_NR_ATUALIZA
                ,B.BLPP_B_RES_BL
                ,B.BLPP_VL_BLOQUEADO
                ,B.BLPP_DT_BLOQUEIO
                ,B.BLPP_PROC_NR
                ,B.BLPP_VLEP_PEDI_UA_CD
                ,B.BLPP_VLEP_PEDI_AN_EX
                ,B.BLPP_VLEP_PEDI_NR_SQ
                ,B.BLPP_VLEP_NR_SQ
                ,B.BLPP_B_PERDCOMP
                ,B.BLPP_DARD_PGTO_NR_ID_AR
                ,B.BLPP_DARD_NR_SQ
            );
        END LOOP; -- END LOOP na tabela BLOQUEIO_PGTO_PROCS (PRODUCAO)

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_PGTO || ' ja se encontra em PR_INSERE_BLOQUEIOS_PGTO.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela BLOQUEIO_PGTO_PROCS.');

            RAISE;
        END;
    END PR_INSERE_BLOQUEIOS_PGTO;


-- Procedimento para insercao na tabela de restituicao
PROCEDURE PR_INSERE_COMPLEMENTO (
     NUM_INCONSISTENCIA IN NUMBER
    ,NUM_DOCUMENTO      IN NUMBER
) IS
    BEGIN
        FOR DC IN (
            SELECT
                 DARC_PGTO_NR_ID_AR
                ,DARC_NR_SQ
                ,DARC_PGTO_NR
                ,DARC_DT_RECEP_INF_PGTO
                ,DARC_CD_BCO
                ,DARC_CD_AGENCIA_BCO
                ,DARC_DT_REGISTRO
                ,DARC_RAGB_NR_ID
                ,DARC_NR_CTRL_ENV_IFC
                ,DARC_IN_MEIO_ENV_IFC
                ,DARC_NR_AUTENTIC_PGTO
                ,DARC_IN_SIT_DEB_AUTOMAT
                ,DARC_NR_CTRL_ENV_STN
                ,DARC_DT_ENV_INF_IFC
                ,DARC_DT_ENV_INF_STN
                ,DARC_NR_CTA_DEB
                ,DARC_NR_CTRL_SIST_OG
                ,DARC_IN_MEIO_ENV_STN
                ,DARC_B_IN_PASSIVO
                ,DARC_B_IN_HISTORICO
                ,DARC_B_IN_ATUALIZACAO
                ,DARC_B_DT_ATUALIZACAO
                ,DARC_B_IN_DELECAO_LOGICA
                ,DARC_B_UA_CD_ATUALIZA
                ,DARC_B_NR_TERMINAL
                ,DARC_B_PF_NR_ATUALIZA
                ,DARC_IN_MEIO_CTRL_SIS_ORIG
                ,DARC_DOVR_NR_SQ
                ,DARC_IN_MEIO_RECEP_INF_PGTO
                ,DARC_NR_CTRL_RECEP_INF_PGTO
                ,DARC_B_CD_MUNI_AG_BANCO_SPU
                ,DARC_B_DT_ENVIO_SPU
                ,DARC_B_DT_ENVIO_CLACON
                --,DARC_B_DT_ENVIO_DW
            FROM
                DOC_ARREC_COMPLEMS@DL_POC08_EXTRATOR -- @DB_LINK_EXTRATOR
            WHERE
                DARC_PGTO_NR_ID_AR = NUM_DOCUMENTO
                OR DARC_DOVR_NR_SQ = NUM_INCONSISTENCIA
        ) LOOP -- LOOP na tabela DOC_ARREC_COMPLEMS (PRODUCAO)
            INSERT INTO DOC_ARREC_COMPLEMS (
                 DARC_PGTO_NR_ID_AR
                ,DARC_NR_SQ
                ,DARC_PGTO_NR
                ,DARC_DT_RECEP_INF_PGTO
                ,DARC_CD_BCO
                ,DARC_CD_AGENCIA_BCO
                ,DARC_DT_REGISTRO
                ,DARC_RAGB_NR_ID
                ,DARC_NR_CTRL_ENV_IFC
                ,DARC_IN_MEIO_ENV_IFC
                ,DARC_NR_AUTENTIC_PGTO
                ,DARC_IN_SIT_DEB_AUTOMAT
                ,DARC_NR_CTRL_ENV_STN
                ,DARC_DT_ENV_INF_IFC
                ,DARC_DT_ENV_INF_STN
                ,DARC_NR_CTA_DEB
                ,DARC_NR_CTRL_SIST_OG
                ,DARC_IN_MEIO_ENV_STN
                ,DARC_B_IN_PASSIVO
                ,DARC_B_IN_HISTORICO
                ,DARC_B_IN_ATUALIZACAO
                ,DARC_B_DT_ATUALIZACAO
                ,DARC_B_IN_DELECAO_LOGICA
                ,DARC_B_UA_CD_ATUALIZA
                ,DARC_B_NR_TERMINAL
                ,DARC_B_PF_NR_ATUALIZA
                ,DARC_IN_MEIO_CTRL_SIS_ORIG
                ,DARC_DOVR_NR_SQ
                ,DARC_IN_MEIO_RECEP_INF_PGTO
                ,DARC_NR_CTRL_RECEP_INF_PGTO
                ,DARC_B_CD_MUNI_AG_BANCO_SPU
                ,DARC_B_DT_ENVIO_SPU
                ,DARC_B_DT_ENVIO_CLACON
                --,DARC_B_DT_ENVIO_DW
            ) VALUES (
                 DC.DARC_PGTO_NR_ID_AR
                ,DC.DARC_NR_SQ
                ,DC.DARC_PGTO_NR
                ,DC.DARC_DT_RECEP_INF_PGTO
                ,DC.DARC_CD_BCO
                ,DC.DARC_CD_AGENCIA_BCO
                ,DC.DARC_DT_REGISTRO
                ,DC.DARC_RAGB_NR_ID
                ,DC.DARC_NR_CTRL_ENV_IFC
                ,DC.DARC_IN_MEIO_ENV_IFC
                ,DC.DARC_NR_AUTENTIC_PGTO
                ,DC.DARC_IN_SIT_DEB_AUTOMAT
                ,DC.DARC_NR_CTRL_ENV_STN
                ,DC.DARC_DT_ENV_INF_IFC
                ,DC.DARC_DT_ENV_INF_STN
                ,DC.DARC_NR_CTA_DEB
                ,DC.DARC_NR_CTRL_SIST_OG
                ,DC.DARC_IN_MEIO_ENV_STN
                ,DC.DARC_B_IN_PASSIVO
                ,DC.DARC_B_IN_HISTORICO
                ,DC.DARC_B_IN_ATUALIZACAO
                ,DC.DARC_B_DT_ATUALIZACAO
                ,DC.DARC_B_IN_DELECAO_LOGICA
                ,DC.DARC_B_UA_CD_ATUALIZA
                ,DC.DARC_B_NR_TERMINAL
                ,DC.DARC_B_PF_NR_ATUALIZA
                ,DC.DARC_IN_MEIO_CTRL_SIS_ORIG
                ,DC.DARC_DOVR_NR_SQ
                ,DC.DARC_IN_MEIO_RECEP_INF_PGTO
                ,DC.DARC_NR_CTRL_RECEP_INF_PGTO
                ,DC.DARC_B_CD_MUNI_AG_BANCO_SPU
                ,DC.DARC_B_DT_ENVIO_SPU
                ,DC.DARC_B_DT_ENVIO_CLACON
                --,DC.DARC_B_DT_ENVIO_DW
            );
        END LOOP; -- END LOOP na tabela DOC_ARREC_COMPLEMS (PRODUCAO)

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_DOCUMENTO || ' ja se encontra em PR_INSERE_COMPLEMENTO.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela DOC_ARREC_COMPLEMS.');

            RAISE;
        END;
    END PR_INSERE_COMPLEMENTO;


PROCEDURE PR_INSERE_VALOR_RECEITAS (
     NUM_PAGAMENTO IN NUMBER
    ,NUM_DOCUMENTO IN NUMBER
    ,SEQ_DESMEMBS  IN NUMBER
) IS
    BEGIN
        FOR VR IN (
            SELECT
                 VLRC_NR_ID
                ,VLRC_NR_SQ
                ,VLRC_DT_REGISTRO
                ,VLRC_VL
                ,VLRC_REC_CD
                ,VLRC_IN_CLASS_RECEITA
                ,VLRC_B_IN_PASSIVO
                ,VLRC_B_IN_HISTORICO
                ,VLRC_B_IN_ATUALIZACAO
                ,VLRC_B_DT_ATUALIZACAO
                ,VLRC_B_IN_DELECAO_LOGICA
                ,VLRC_HPCO_CCST_SG
                ,VLRC_HPCO_NR_SQ
                ,VLRC_DARD_PGTO_NR_ID_AR
                ,VLRC_DARD_NR_SQ
                ,VLRC_PGTO_NR_ID_AR
                ,VLRC_PGTO_NR
                ,VLRC_TPFR_CD
                ,VLRC_B_UA_CD_ATUALIZA
                ,VLRC_B_NR_TERMINAL
                ,VLRC_B_PF_NR_ATUALIZA
                ,VLRC_B_VL
                ,VLRC_ECRC_REC_CD
                ,VLRC_ECRC_CD
            FROM
                VALOR_RECEITAS@DL_POC08_EXTRATOR
            WHERE
                (VLRC_DARD_PGTO_NR_ID_AR = NUM_DOCUMENTO
                AND VLRC_DARD_NR_SQ = SEQ_DESMEMBS)
                OR VLRC_PGTO_NR = NUM_PAGAMENTO
        ) LOOP
            INSERT INTO VALOR_RECEITAS (
                 VLRC_NR_ID
                ,VLRC_NR_SQ
                ,VLRC_DT_REGISTRO
                ,VLRC_VL
                ,VLRC_REC_CD
                ,VLRC_IN_CLASS_RECEITA
                ,VLRC_B_IN_PASSIVO
                ,VLRC_B_IN_HISTORICO
                ,VLRC_B_IN_ATUALIZACAO
                ,VLRC_B_DT_ATUALIZACAO
                ,VLRC_B_IN_DELECAO_LOGICA
                ,VLRC_HPCO_CCST_SG
                ,VLRC_HPCO_NR_SQ
                ,VLRC_DARD_PGTO_NR_ID_AR
                ,VLRC_DARD_NR_SQ
                ,VLRC_PGTO_NR_ID_AR
                ,VLRC_PGTO_NR
                ,VLRC_TPFR_CD
                ,VLRC_B_UA_CD_ATUALIZA
                ,VLRC_B_NR_TERMINAL
                ,VLRC_B_PF_NR_ATUALIZA
                ,VLRC_B_VL
                ,VLRC_ECRC_REC_CD
                ,VLRC_ECRC_CD
            ) VALUES (
                 VR.VLRC_NR_ID
                ,VR.VLRC_NR_SQ
                ,VR.VLRC_DT_REGISTRO
                ,VR.VLRC_VL
                ,VR.VLRC_REC_CD
                ,VR.VLRC_IN_CLASS_RECEITA
                ,VR.VLRC_B_IN_PASSIVO
                ,VR.VLRC_B_IN_HISTORICO
                ,VR.VLRC_B_IN_ATUALIZACAO
                ,VR.VLRC_B_DT_ATUALIZACAO
                ,VR.VLRC_B_IN_DELECAO_LOGICA
                ,VR.VLRC_HPCO_CCST_SG
                ,VR.VLRC_HPCO_NR_SQ
                ,VR.VLRC_DARD_PGTO_NR_ID_AR
                ,VR.VLRC_DARD_NR_SQ
                ,VR.VLRC_PGTO_NR_ID_AR
                ,VR.VLRC_PGTO_NR
                ,VR.VLRC_TPFR_CD
                ,VR.VLRC_B_UA_CD_ATUALIZA
                ,VR.VLRC_B_NR_TERMINAL
                ,VR.VLRC_B_PF_NR_ATUALIZA
                ,VR.VLRC_B_VL
                ,VR.VLRC_ECRC_REC_CD
                ,VR.VLRC_ECRC_CD
            );
        END LOOP;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_PAGAMENTO || ' ja se encontra em PR_INSERE_VALOR_RECEITAS.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela VALOR_RECEITAS.');

            RAISE;
        END;
    END PR_INSERE_VALOR_RECEITAS;


-- Procedimento para insercao na tabela de desdobramento
PROCEDURE PR_INSERE_DESMEMBRAMENTO (
     NUM_PAGAMENTO IN NUMBER
    ,NUM_DOCUMENTO IN NUMBER
) IS
    BEGIN
        FOR D IN (
            SELECT
                 DARD_PGTO_NR_ID_AR
                ,DARD_NR_SQ
                ,DARD_PGTO_NR
                ,DARD_CD_GPO_DOC
                ,DARD_DT_REGISTRO
                ,DARD_DT_ENCERRA_PER_APU
                ,DARD_DT_VENCIMENTO
                ,DARD_IN_DESTINACAO
                ,DARD_VL_TOTAL
                ,DARD_TPSI_CD
                ,DARD_NR_REFERENCIA
                ,DARD_NR_ID_DEPOSITO_CEF
                ,DARD_NR_PROCESSO
                ,DARD_MUNI_CD
                ,DARD_UF_SG
                ,DARD_TBFR_CD
                ,DARD_UA_CLASS_CD
                ,DARD_B_MUNIC_CLAS_CD
                ,DARD_B_IN_PASSIVO
                ,DARD_B_IN_HISTORICO
                ,DARD_B_IN_ATUALIZACAO
                ,DARD_B_DT_ATUALIZACAO
                ,DARD_B_IN_DELECAO_LOGICA
                ,DARD_B_UA_CD_ATUALIZA
                ,DARD_B_NR_TERMINAL
                ,DARD_B_PF_NR_ATUALIZA
                ,DARD_B_VL_P
                ,DARD_B_VL_M
                ,DARD_B_VL_J
                ,DARD_B_VL_DP
                ,DARD_B_VL_DM
                ,DARD_B_VL_DJ
                ,DARD_ENTE_CD
                ,DARD_NR_ID_PARCEL
                ,DARD_UA_CLASS_PGFPGFN
                ,DARD_IN_ORIGEM_AR
                ,DARD_TEC_CD
        ,DARD_RGSU_NR_SQ
        ,DARD_IN_OCORRENCIA
        ,DARD_NR_INSCR_DAU
        ,DARD_NR_NIRF
        ,DARD_NR_CNO
        ,DARD_NR_NIT
        ,DARD_IND_DEC_SAL
        ,DARD_CNPJ_PRESTADOR
        ,DARD_CPF_SEGURADO
        ,DARD_MUNIC_CLAS_CD
        ,DARD_ENTE_NR_SQ
        ,DARD_B_UG
                ,DARD_B_GESTAO
            FROM
                DOC_ARREC_DESMEMBS@DL_POC08_EXTRATOR
            WHERE
                DARD_PGTO_NR_ID_AR = NUM_DOCUMENTO
        ) LOOP
            INSERT INTO DOC_ARREC_DESMEMBS (
                 DARD_PGTO_NR_ID_AR
                ,DARD_NR_SQ
                ,DARD_PGTO_NR
                ,DARD_CD_GPO_DOC
                ,DARD_DT_REGISTRO
                ,DARD_DT_ENCERRA_PER_APU
                ,DARD_DT_VENCIMENTO
                ,DARD_IN_DESTINACAO
                ,DARD_VL_TOTAL
                ,DARD_TPSI_CD
                ,DARD_NR_REFERENCIA
                ,DARD_NR_ID_DEPOSITO_CEF
                ,DARD_NR_PROCESSO
                ,DARD_MUNI_CD
                ,DARD_UF_SG
                ,DARD_TBFR_CD
                ,DARD_UA_CLASS_CD
                ,DARD_B_MUNIC_CLAS_CD
                ,DARD_B_IN_PASSIVO
                ,DARD_B_IN_HISTORICO
                ,DARD_B_IN_ATUALIZACAO
                ,DARD_B_DT_ATUALIZACAO
                ,DARD_B_IN_DELECAO_LOGICA
                ,DARD_B_UA_CD_ATUALIZA
                ,DARD_B_NR_TERMINAL
                ,DARD_B_PF_NR_ATUALIZA
                ,DARD_B_VL_P
                ,DARD_B_VL_M
                ,DARD_B_VL_J
                ,DARD_B_VL_DP
                ,DARD_B_VL_DM
                ,DARD_B_VL_DJ
                ,DARD_ENTE_CD
                ,DARD_NR_ID_PARCEL
                ,DARD_UA_CLASS_PGFPGFN
                ,DARD_IN_ORIGEM_AR
                ,DARD_TEC_CD
        ,DARD_RGSU_NR_SQ
        ,DARD_IN_OCORRENCIA
        ,DARD_NR_INSCR_DAU
        ,DARD_NR_NIRF
        ,DARD_NR_CNO
        ,DARD_NR_NIT
        ,DARD_IND_DEC_SAL
        ,DARD_CNPJ_PRESTADOR
        ,DARD_CPF_SEGURADO
        ,DARD_MUNIC_CLAS_CD
        ,DARD_ENTE_NR_SQ
        ,DARD_B_UG
                ,DARD_B_GESTAO
            ) VALUES (
                 D.DARD_PGTO_NR_ID_AR
                ,D.DARD_NR_SQ
                ,D.DARD_PGTO_NR
                ,D.DARD_CD_GPO_DOC
                ,D.DARD_DT_REGISTRO
                ,D.DARD_DT_ENCERRA_PER_APU
                ,D.DARD_DT_VENCIMENTO
                ,D.DARD_IN_DESTINACAO
                ,D.DARD_VL_TOTAL
                ,D.DARD_TPSI_CD
                ,D.DARD_NR_REFERENCIA
                ,D.DARD_NR_ID_DEPOSITO_CEF
                ,D.DARD_NR_PROCESSO
                ,D.DARD_MUNI_CD
                ,D.DARD_UF_SG
                ,D.DARD_TBFR_CD
                ,D.DARD_UA_CLASS_CD
                ,D.DARD_B_MUNIC_CLAS_CD
                ,D.DARD_B_IN_PASSIVO
                ,D.DARD_B_IN_HISTORICO
                ,D.DARD_B_IN_ATUALIZACAO
                ,D.DARD_B_DT_ATUALIZACAO
                ,D.DARD_B_IN_DELECAO_LOGICA
                ,D.DARD_B_UA_CD_ATUALIZA
                ,D.DARD_B_NR_TERMINAL
                ,D.DARD_B_PF_NR_ATUALIZA
                ,D.DARD_B_VL_P
                ,D.DARD_B_VL_M
                ,D.DARD_B_VL_J
                ,D.DARD_B_VL_DP
                ,D.DARD_B_VL_DM
                ,D.DARD_B_VL_DJ
                ,D.DARD_ENTE_CD
                ,D.DARD_NR_ID_PARCEL
                ,D.DARD_UA_CLASS_PGFPGFN
                ,D.DARD_IN_ORIGEM_AR
                ,D.DARD_TEC_CD
        ,D.DARD_RGSU_NR_SQ
        ,D.DARD_IN_OCORRENCIA
        ,D.DARD_NR_INSCR_DAU
        ,D.DARD_NR_NIRF
        ,D.DARD_NR_CNO
        ,D.DARD_NR_NIT
        ,D.DARD_IND_DEC_SAL
        ,D.DARD_CNPJ_PRESTADOR
        ,D.DARD_CPF_SEGURADO
        ,D.DARD_MUNIC_CLAS_CD
        ,D.DARD_ENTE_NR_SQ
        ,D.DARD_B_UG
                ,D.DARD_B_GESTAO
            );

            PR_INSERE_VALOR_RECEITAS(NUM_PAGAMENTO, NUM_DOCUMENTO,D.DARD_NR_SQ);
        END LOOP;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_PAGAMENTO || ' ja se encontra em PR_INSERE_DESMEMBRAMENTO.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela DOC_ARREC_DESMEMBS.');

            RAISE;
        END;
    END PR_INSERE_DESMEMBRAMENTO;


-- Procedimento para insercao na tabela de classificacao
PROCEDURE PR_INSERE_CLASSIFICACAO (
     NUM_PAGAMENTO IN NUMBER
    ,NUM_DOCUMENTO IN NUMBER
) IS
    BEGIN
        FOR CR IN (
            SELECT
                 CRDA_NR_ID
                ,CRDA_SQ
                ,CRDA_DT_REGISTRO
                ,CRDA_REC_CD
                ,CRDA_B_IN_TP_RECEITA
                ,CRDA_IN_TP_REC_ORIG
                ,CRDA_VL
                ,CRDA_VLRC_NR_ID
                ,CRDA_VLRC_NR_SQ
                ,CRDA_B_IN_PASSIVO
                ,CRDA_B_IN_HISTORICO
                ,CRDA_B_IN_ATUALIZACAO
                ,CRDA_B_DT_ATUALIZACAO
                ,CRDA_B_IN_DELECAO_LOGICA
                ,CRDA_IN_CLASSIFICACAO
                ,CRDA_UA_CLASS_CD
                ,CRDA_DARD_PGTO_NR_ID_AR
                ,CRDA_DARD_NR_SQ
                ,CRDA_PGTO_NR_ID_AR
                ,CRDA_PGTO_NR
                ,CRDA_UF_SG
                ,CRDA_TBFR_CD
                ,CRDA_MUNI_CD
                ,CRDA_B_UA_CD_ATUALIZA
                ,CRDA_B_NR_TERMINAL
                ,CRDA_B_PF_NR_ATUALIZA
                ,CRDA_B_MUNIC_CLAS_CD
                ,CRDA_ENTE_CD
                ,CRDA_MPAR_NR_ID
                ,CRDA_UA_CLASS_PGFPGFN
                ,CRDA_IN_ORIGEM_AR
                ,CRDA_TEC_CD
            FROM
                CLASSIFICA_REC_DAS@DL_POC08_EXTRATOR
            WHERE
                CRDA_NR_ID = NUM_DOCUMENTO
        ) LOOP
            INSERT INTO CLASSIFICA_REC_DAS (
                 CRDA_NR_ID
                ,CRDA_SQ
                ,CRDA_DT_REGISTRO
                ,CRDA_REC_CD
                ,CRDA_B_IN_TP_RECEITA
                ,CRDA_IN_TP_REC_ORIG
                ,CRDA_VL
                ,CRDA_VLRC_NR_ID
                ,CRDA_VLRC_NR_SQ
                ,CRDA_B_IN_PASSIVO
                ,CRDA_B_IN_HISTORICO
                ,CRDA_B_IN_ATUALIZACAO
                ,CRDA_B_DT_ATUALIZACAO
                ,CRDA_B_IN_DELECAO_LOGICA
                ,CRDA_IN_CLASSIFICACAO
                ,CRDA_UA_CLASS_CD
                ,CRDA_DARD_PGTO_NR_ID_AR
                ,CRDA_DARD_NR_SQ
                ,CRDA_PGTO_NR_ID_AR
                ,CRDA_PGTO_NR
                ,CRDA_UF_SG
                ,CRDA_TBFR_CD
                ,CRDA_MUNI_CD
                ,CRDA_B_UA_CD_ATUALIZA
                ,CRDA_B_NR_TERMINAL
                ,CRDA_B_PF_NR_ATUALIZA
                ,CRDA_B_MUNIC_CLAS_CD
                ,CRDA_ENTE_CD
                ,CRDA_MPAR_NR_ID
                ,CRDA_UA_CLASS_PGFPGFN
                ,CRDA_IN_ORIGEM_AR
                ,CRDA_TEC_CD
            ) VALUES (
                 CR.CRDA_NR_ID
                ,CR.CRDA_SQ
                ,CR.CRDA_DT_REGISTRO
                ,CR.CRDA_REC_CD
                ,CR.CRDA_B_IN_TP_RECEITA
                ,CR.CRDA_IN_TP_REC_ORIG
                ,CR.CRDA_VL
                ,CR.CRDA_VLRC_NR_ID
                ,CR.CRDA_VLRC_NR_SQ
                ,CR.CRDA_B_IN_PASSIVO
                ,CR.CRDA_B_IN_HISTORICO
                ,CR.CRDA_B_IN_ATUALIZACAO
                ,CR.CRDA_B_DT_ATUALIZACAO
                ,CR.CRDA_B_IN_DELECAO_LOGICA
                ,CR.CRDA_IN_CLASSIFICACAO
                ,CR.CRDA_UA_CLASS_CD
                ,CR.CRDA_DARD_PGTO_NR_ID_AR
                ,CR.CRDA_DARD_NR_SQ
                ,CR.CRDA_PGTO_NR_ID_AR
                ,CR.CRDA_PGTO_NR
                ,CR.CRDA_UF_SG
                ,CR.CRDA_TBFR_CD
                ,CR.CRDA_MUNI_CD
                ,CR.CRDA_B_UA_CD_ATUALIZA
                ,CR.CRDA_B_NR_TERMINAL
                ,CR.CRDA_B_PF_NR_ATUALIZA
                ,CR.CRDA_B_MUNIC_CLAS_CD
                ,CR.CRDA_ENTE_CD
                ,CR.CRDA_MPAR_NR_ID
                ,CR.CRDA_UA_CLASS_PGFPGFN
                ,CR.CRDA_IN_ORIGEM_AR
                ,CR.CRDA_TEC_CD
            );
        END LOOP;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_PAGAMENTO || ' ja se encontra em PR_INSERE_CLASSIFICACAO.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela CLASSIFICA_REC_DAS.');

            RAISE;
        END;
    END PR_INSERE_CLASSIFICACAO;


-- Procedimento para insercao na tabela de restituicao
PROCEDURE PR_INSERE_INCONSISTENCIA (
     NUM_DOCUMENTO IN NUMBER
) IS
    CONTADOR NUMBER;

    BEGIN
        FOR V IN (
            SELECT DISTINCT
                 DOVR_NR_SQ
                ,DOVR_NR_ID
                ,DOVR_VL_DOC_INF_AAR
                ,DOVR_DT_REGISTRO
                ,DOVR_DT_ARRECADACAO
                ,DOVR_CD_CONVENIO
                ,DOVR_CD_REPRES_BARRA
                ,DOVR_ESTB_PJ_NR
                ,DOVR_ESTB_NR
                ,DOVR_TQRP_CD
                ,DOVR_TRRD_CD
                ,DOVR_PSAR_CD_CPS
                ,DOVR_PGTO_NR
                ,DOVR_TIFP_CD
                ,DOVR_CD_AGENCIA
                ,DOVR_TMAR_CD
                ,DOVR_NR_EXTRA_BARRA
                ,DOVR_DT_TRATAMENTO
                ,DOVR_B_UA_CD_ATUALIZA
                ,DOVR_B_NR_TERMINAL
                ,DOVR_B_PF_NR_ATUALIZA
                ,DOVR_B_IN_PASSIVO
                ,DOVR_B_IN_HISTORICO
                ,DOVR_B_IN_ATUALIZACAO
                ,DOVR_B_DT_ATUALIZACAO
                ,DOVR_B_IN_DELECAO_LOGICA
                ,DOVR_IN_CLASSIFICACAO
                ,DOVR_DT_RECEP_PG_RF
                ,DOVR_IN_MEIO_RECEP_PG_RF
                ,DOVR_NR_CTRL_RECEP_PG_RF
                ,DOVR_REC_CD
                ,DOVR_DT_APROPRIACAO
                ,DOVR_DT_PERIODO_APROPRIA
            FROM
                DOC_AR_OBJETO_VERIFS@DL_POC08_EXTRATOR
            WHERE
                DOVR_NR_EXTRA_BARRA = NUM_DOCUMENTO
                OR DOVR_NR_ID       = NUM_DOCUMENTO
        ) LOOP
            INSERT INTO DOC_AR_OBJETO_VERIFS (
                 DOVR_NR_SQ
                ,DOVR_NR_ID
                ,DOVR_VL_DOC_INF_AAR
                ,DOVR_DT_REGISTRO
                ,DOVR_DT_ARRECADACAO
                ,DOVR_CD_CONVENIO
                ,DOVR_CD_REPRES_BARRA
                ,DOVR_ESTB_PJ_NR
                ,DOVR_ESTB_NR
                ,DOVR_TQRP_CD
                ,DOVR_TRRD_CD
                ,DOVR_PSAR_CD_CPS
                ,DOVR_PGTO_NR
                ,DOVR_TIFP_CD
                ,DOVR_CD_AGENCIA
                ,DOVR_TMAR_CD
                ,DOVR_NR_EXTRA_BARRA
                ,DOVR_DT_TRATAMENTO
                ,DOVR_B_UA_CD_ATUALIZA
                ,DOVR_B_NR_TERMINAL
                ,DOVR_B_PF_NR_ATUALIZA
                ,DOVR_B_IN_PASSIVO
                ,DOVR_B_IN_HISTORICO
                ,DOVR_B_IN_ATUALIZACAO
                ,DOVR_B_DT_ATUALIZACAO
                ,DOVR_B_IN_DELECAO_LOGICA
                ,DOVR_IN_CLASSIFICACAO
                ,DOVR_DT_RECEP_PG_RF
                ,DOVR_IN_MEIO_RECEP_PG_RF
                ,DOVR_NR_CTRL_RECEP_PG_RF
                ,DOVR_REC_CD
                ,DOVR_DT_APROPRIACAO
                ,DOVR_DT_PERIODO_APROPRIA
            ) VALUES (
                 V.DOVR_NR_SQ
                ,V.DOVR_NR_ID
                ,V.DOVR_VL_DOC_INF_AAR
                ,V.DOVR_DT_REGISTRO
                ,V.DOVR_DT_ARRECADACAO
                ,V.DOVR_CD_CONVENIO
                ,V.DOVR_CD_REPRES_BARRA
                ,V.DOVR_ESTB_PJ_NR
                ,V.DOVR_ESTB_NR
                ,V.DOVR_TQRP_CD
                ,V.DOVR_TRRD_CD
                ,V.DOVR_PSAR_CD_CPS
                ,V.DOVR_PGTO_NR
                ,V.DOVR_TIFP_CD
                ,V.DOVR_CD_AGENCIA
                ,V.DOVR_TMAR_CD
                ,V.DOVR_NR_EXTRA_BARRA
                ,V.DOVR_DT_TRATAMENTO
                ,V.DOVR_B_UA_CD_ATUALIZA
                ,V.DOVR_B_NR_TERMINAL
                ,V.DOVR_B_PF_NR_ATUALIZA
                ,V.DOVR_B_IN_PASSIVO
                ,V.DOVR_B_IN_HISTORICO
                ,V.DOVR_B_IN_ATUALIZACAO
                ,V.DOVR_B_DT_ATUALIZACAO
                ,V.DOVR_B_IN_DELECAO_LOGICA
                ,V.DOVR_IN_CLASSIFICACAO
                ,V.DOVR_DT_RECEP_PG_RF
                ,V.DOVR_IN_MEIO_RECEP_PG_RF
                ,V.DOVR_NR_CTRL_RECEP_PG_RF
                ,V.DOVR_REC_CD
                ,V.DOVR_DT_APROPRIACAO
                ,V.DOVR_DT_PERIODO_APROPRIA
            );

            IF (V.DOVR_NR_ID IS NOT NULL) THEN
                PR_INSERE_COMPLEMENTO(V.DOVR_NR_SQ, NULL);
            END IF;
        END LOOP;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_DOCUMENTO || ' ja se encontra em PR_INSERE_INCONSISTENCIA.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela DOC_AR_OBJETO_VERIFS.');

            RAISE;
        END;
    END PR_INSERE_INCONSISTENCIA;


PROCEDURE PR_INSERE_OPER_CONTABIL (
     NUM_DOCUMENTO IN NUMBER
) IS
    BEGIN
        FOR OP IN (
            SELECT
                 OCPG_NR_ID
                ,OCPG_NR_SQ
                ,OCPG_IN_EFEITO_CONTABIL
                ,OCPG_DT_OPERACAO
                ,OCPG_DT_REGISTRO
                ,OCPG_VL
                ,OCPG_B_IN_PASSIVO
                ,OCPG_B_IN_HISTORICO
                ,OCPG_B_IN_ATUALIZACAO
                ,OCPG_B_DT_ATUALIZACAO
                ,OCPG_B_IN_DELECAO_LOGICA
                ,OCPG_DARD_PGTO_NR_ID_AR
                ,OCPG_DARD_NR_SQ
                ,OCPG_BLPP_PGTO_NR
                ,OCPG_BLPP_NR_SQ
                ,OCPG_ALOC_UA_CD
                ,OCPG_ALOC_AN_EX
                ,OCPG_ALOC_NR_SQ
                ,OCPG_ACDP_PGTO_NR
                ,OCPG_ACDP_DEGU_NR_SQ
                ,OCPG_ACDP_DVIC_NR_SQ
                ,OCPG_ACDP_NR_SQ
                ,OCPG_PF_NR
                ,OCPG_TOCP_CD
                ,OCPG_TPSI_CD
                ,OCPG_TPFR_CD
                ,OCPG_PGTO_NR
                ,OCPG_B_UA_CD_ATUALIZA
                ,OCPG_B_NR_TERMINAL
                ,OCPG_B_PF_NR_ATUALIZA
                ,OCPG_TX_EXPLICATIVO
                ,OCPG_B_IP_TERMINAL
                ,OCPG_LREC_NR_LEVANTAMENTO
                ,OCPG_B_CD_SALDO_DJE
            FROM
                OPER_CONTABIL_PGTOS@DL_POC08_EXTRATOR
            WHERE
                OCPG_NR_ID = NUM_DOCUMENTO
        ) LOOP
            INSERT INTO OPER_CONTABIL_PGTOS (
                 OCPG_NR_ID
                ,OCPG_NR_SQ
                ,OCPG_IN_EFEITO_CONTABIL
                ,OCPG_DT_OPERACAO
                ,OCPG_DT_REGISTRO
                ,OCPG_VL
                ,OCPG_B_IN_PASSIVO
                ,OCPG_B_IN_HISTORICO
                ,OCPG_B_IN_ATUALIZACAO
                ,OCPG_B_DT_ATUALIZACAO
                ,OCPG_B_IN_DELECAO_LOGICA
                ,OCPG_DARD_PGTO_NR_ID_AR
                ,OCPG_DARD_NR_SQ
                ,OCPG_BLPP_PGTO_NR
                ,OCPG_BLPP_NR_SQ
                ,OCPG_ALOC_UA_CD
                ,OCPG_ALOC_AN_EX
                ,OCPG_ALOC_NR_SQ
                ,OCPG_ACDP_PGTO_NR
                ,OCPG_ACDP_DEGU_NR_SQ
                ,OCPG_ACDP_DVIC_NR_SQ
                ,OCPG_ACDP_NR_SQ
                ,OCPG_PF_NR
                ,OCPG_TOCP_CD
                ,OCPG_TPSI_CD
                ,OCPG_TPFR_CD
                ,OCPG_PGTO_NR
                ,OCPG_B_UA_CD_ATUALIZA
                ,OCPG_B_NR_TERMINAL
                ,OCPG_B_PF_NR_ATUALIZA
                ,OCPG_TX_EXPLICATIVO
                ,OCPG_B_IP_TERMINAL
                ,OCPG_LREC_NR_LEVANTAMENTO
                ,OCPG_B_CD_SALDO_DJE
            ) VALUES (
                 OP.OCPG_NR_ID
                ,OP.OCPG_NR_SQ
                ,OP.OCPG_IN_EFEITO_CONTABIL
                ,OP.OCPG_DT_OPERACAO
                ,OP.OCPG_DT_REGISTRO
                ,OP.OCPG_VL
                ,OP.OCPG_B_IN_PASSIVO
                ,OP.OCPG_B_IN_HISTORICO
                ,OP.OCPG_B_IN_ATUALIZACAO
                ,OP.OCPG_B_DT_ATUALIZACAO
                ,OP.OCPG_B_IN_DELECAO_LOGICA
                ,OP.OCPG_DARD_PGTO_NR_ID_AR
                ,OP.OCPG_DARD_NR_SQ
                ,OP.OCPG_BLPP_PGTO_NR
                ,OP.OCPG_BLPP_NR_SQ
                ,OP.OCPG_ALOC_UA_CD
                ,OP.OCPG_ALOC_AN_EX
                ,OP.OCPG_ALOC_NR_SQ
                ,OP.OCPG_ACDP_PGTO_NR
                ,OP.OCPG_ACDP_DEGU_NR_SQ
                ,OP.OCPG_ACDP_DVIC_NR_SQ
                ,OP.OCPG_ACDP_NR_SQ
                ,OP.OCPG_PF_NR
                ,OP.OCPG_TOCP_CD
                ,OP.OCPG_TPSI_CD
                ,OP.OCPG_TPFR_CD
                ,OP.OCPG_PGTO_NR
                ,OP.OCPG_B_UA_CD_ATUALIZA
                ,OP.OCPG_B_NR_TERMINAL
                ,OP.OCPG_B_PF_NR_ATUALIZA
                ,OP.OCPG_TX_EXPLICATIVO
                ,OP.OCPG_B_IP_TERMINAL
                ,OP.OCPG_LREC_NR_LEVANTAMENTO
                ,OP.OCPG_B_CD_SALDO_DJE
            );
        END LOOP;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_DOCUMENTO || ' ja se encontra em PR_INSERE_OPER_CONTABIL.');
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela OPER_CONTABIL_PGTOS.');

            RAISE;
        END;
    END PR_INSERE_OPER_CONTABIL;


PROCEDURE PR_INSERE_REMESSA_LEVANTAMENTO (
     NUM_REMESSA IN NUMBER
) IS
    CONTADOR NUMBER;

    BEGIN
         FOR R IN (
            SELECT
                 RELE_NR_ID
                ,RELE_DT_REGISTRO
                ,RELE_DT_GERACAO
                ,RELE_IN_TP_REMESSA
                ,RELE_B_QT_LEVANTAMENTO
                ,RELE_B_VL_TOTAL_DEVOL_LEV
                ,RELE_B_VL_TOTAL_AJUSTE_LEV
                ,RELE_B_VL_TOTAL_TRANSF_LEV
                ,RELE_B_IN_HISTORICO
                ,RELE_B_IN_PASSIVO
                ,RELE_B_UA_CD_ATUALIZA
                ,RELE_B_PF_NR_ATUALIZA
                ,RELE_B_NR_TERMINAL
            FROM
                REMESSA_LEVANTAMENTOS@DL_POC08_EXTRATOR
            WHERE
                RELE_NR_ID = NUM_REMESSA
        ) LOOP
            INSERT INTO REMESSA_LEVANTAMENTOS (
                 RELE_NR_ID
                ,RELE_DT_REGISTRO
                ,RELE_DT_GERACAO
                ,RELE_IN_TP_REMESSA
                ,RELE_B_QT_LEVANTAMENTO
                ,RELE_B_VL_TOTAL_DEVOL_LEV
                ,RELE_B_VL_TOTAL_AJUSTE_LEV
                ,RELE_B_VL_TOTAL_TRANSF_LEV
                ,RELE_B_IN_HISTORICO
                ,RELE_B_IN_PASSIVO
                ,RELE_B_UA_CD_ATUALIZA
                ,RELE_B_PF_NR_ATUALIZA
                ,RELE_B_NR_TERMINAL
            ) VALUES (
                 R.RELE_NR_ID
                ,R.RELE_DT_REGISTRO
                ,R.RELE_DT_GERACAO
                ,R.RELE_IN_TP_REMESSA
                ,R.RELE_B_QT_LEVANTAMENTO
                ,R.RELE_B_VL_TOTAL_DEVOL_LEV
                ,R.RELE_B_VL_TOTAL_AJUSTE_LEV
                ,R.RELE_B_VL_TOTAL_TRANSF_LEV
                ,R.RELE_B_IN_HISTORICO
                ,R.RELE_B_IN_PASSIVO
                ,R.RELE_B_UA_CD_ATUALIZA
                ,R.RELE_B_PF_NR_ATUALIZA
                ,R.RELE_B_NR_TERMINAL
            );
        END LOOP;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            -- DBMS_OUTPUT.PUT_LINE('Aviso: ' || NUM_REMESSA || ' ja se encontra em PR_INSERE_REMESSA_LEVANTAMENTO.');
            NULL;
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela REMESSA_LEVANTAMENTOS.');

            RAISE;
        END;
    END PR_INSERE_REMESSA_LEVANTAMENTO;


PROCEDURE PR_INSERE_DJE_REGISTRO (
     DJE IN LEVANTA_REC_DJES%ROWTYPE
) IS
    BEGIN
        INSERT INTO LEVANTA_REC_DJES (
             LRDJ_LREC_NR_LEVANTAMENTO
            ,LRDJ_NR_SQ
            ,LRDJ_DT_REGISTRO
            ,LRDJ_DT_DEPOSITO
            ,LRDJ_DT_VENCIMENTO
            ,LRDJ_VL_DEPOSITO
            ,LRDJ_VL_DEVOLVIDO
            ,LRDJ_VL_JUROS
            ,LRDJ_VL_TRANSFORMADO
            ,LRDJ_B_IN_HISTORICO
            ,LRDJ_B_IN_PASSIVO
            ,LRDJ_B_UA_CD_ATUALIZA
            ,LRDJ_B_PF_NR_ATUALIZA
            ,LRDJ_B_NR_TERMINAL
            ,LRDJ_PGTO_NR
            ,LRDJ_IN_PEND_CANCEL
        ) VALUES (
             DJE.LRDJ_LREC_NR_LEVANTAMENTO
            ,DJE.LRDJ_NR_SQ
            ,DJE.LRDJ_DT_REGISTRO
            ,DJE.LRDJ_DT_DEPOSITO
            ,DJE.LRDJ_DT_VENCIMENTO
            ,DJE.LRDJ_VL_DEPOSITO
            ,DJE.LRDJ_VL_DEVOLVIDO
            ,DJE.LRDJ_VL_JUROS
            ,DJE.LRDJ_VL_TRANSFORMADO
            ,DJE.LRDJ_B_IN_HISTORICO
            ,DJE.LRDJ_B_IN_PASSIVO
            ,DJE.LRDJ_B_UA_CD_ATUALIZA
            ,DJE.LRDJ_B_PF_NR_ATUALIZA
            ,DJE.LRDJ_B_NR_TERMINAL
            ,DJE.LRDJ_PGTO_NR
            ,DJE.LRDJ_IN_PEND_CANCEL
        );

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            -- DBMS_OUTPUT.PUT_LINE('Aviso: ' || DJE.LRDJ_LREC_NR_LEVANTAMENTO || ' ja se encontra em PR_INSERE_DJE_REGISTRO.');
            NULL;
        END;
    END PR_INSERE_DJE_REGISTRO;


PROCEDURE PR_INSERE_LEVANTA_REC_DJES (
     NUM_LEVANTAMENTO IN NUMBER
) IS
    CONTADOR NUMBER;
    DJE      LEVANTA_REC_DJES%ROWTYPE;

    BEGIN
        FOR D IN (
            SELECT
                 LRDJ_LREC_NR_LEVANTAMENTO
                ,LRDJ_NR_SQ
                ,LRDJ_DT_REGISTRO
                ,LRDJ_DT_DEPOSITO
                ,LRDJ_DT_VENCIMENTO
                ,LRDJ_VL_DEPOSITO
                ,LRDJ_VL_DEVOLVIDO
                ,LRDJ_VL_JUROS
                ,LRDJ_VL_TRANSFORMADO
                ,LRDJ_IN_PEND_CANCEL
                ,LRDJ_B_IN_HISTORICO
                ,LRDJ_B_IN_PASSIVO
                ,LRDJ_B_UA_CD_ATUALIZA
                ,LRDJ_B_PF_NR_ATUALIZA
                ,LRDJ_B_NR_TERMINAL
                ,LRDJ_PGTO_NR
            FROM
                LEVANTA_REC_DJES@DL_POC08_EXTRATOR
            WHERE
                LRDJ_LREC_NR_LEVANTAMENTO = NUM_LEVANTAMENTO
        ) LOOP
            DJE.LRDJ_LREC_NR_LEVANTAMENTO := D.LRDJ_LREC_NR_LEVANTAMENTO;
            DJE.LRDJ_NR_SQ                := D.LRDJ_NR_SQ;
            DJE.LRDJ_DT_REGISTRO          := D.LRDJ_DT_REGISTRO;
            DJE.LRDJ_DT_DEPOSITO          := D.LRDJ_DT_DEPOSITO;
            DJE.LRDJ_DT_VENCIMENTO        := D.LRDJ_DT_VENCIMENTO;
            DJE.LRDJ_VL_DEPOSITO          := D.LRDJ_VL_DEPOSITO;
            DJE.LRDJ_VL_DEVOLVIDO         := D.LRDJ_VL_DEVOLVIDO;
            DJE.LRDJ_VL_JUROS             := D.LRDJ_VL_JUROS;
            DJE.LRDJ_VL_TRANSFORMADO      := D.LRDJ_VL_TRANSFORMADO;
            DJE.LRDJ_IN_PEND_CANCEL       := D.LRDJ_IN_PEND_CANCEL;
            DJE.LRDJ_B_IN_HISTORICO       := D.LRDJ_B_IN_HISTORICO;
            DJE.LRDJ_B_IN_PASSIVO         := D.LRDJ_B_IN_PASSIVO;
            DJE.LRDJ_B_UA_CD_ATUALIZA     := D.LRDJ_B_UA_CD_ATUALIZA;
            DJE.LRDJ_B_PF_NR_ATUALIZA     := D.LRDJ_B_PF_NR_ATUALIZA;
            DJE.LRDJ_B_NR_TERMINAL        := D.LRDJ_B_NR_TERMINAL;
            DJE.LRDJ_PGTO_NR              := D.LRDJ_PGTO_NR;

            PR_INSERE_DJE_REGISTRO(DJE);
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela LEVANTA_REC_DJES.');

            RAISE;
        END;
    END PR_INSERE_LEVANTA_REC_DJES;


PROCEDURE PR_INSERE_LEVANTA_REGISTRO (
     LEVANTAMENTO IN LEVANTAMENTO_RECS%ROWTYPE
) IS
    BEGIN
        INSERT INTO LEVANTAMENTO_RECS (
             LREC_NR_LEVANTAMENTO
            ,LREC_DT_REGISTRO
            ,LREC_REC_CD
            ,LREC_RELE_NR_ID
            ,LREC_CDLV_NR_CONTA
            ,LREC_VL_AJUSTE
            ,LREC_IN_PENDENCIA_UTIL
            ,LREC_IN_CANCELADO
            ,LREC_B_IN_PROCESSO
            ,LREC_B_QT_DEPOSITO
            ,LREC_B_VL_TOTAL_DEVOL_DJE
            ,LREC_B_VL_TOTAL_JUROS_DJE
            ,LREC_B_VL_TOTAL_TRANSF_DJE
            ,LREC_B_IN_HISTORICO
            ,LREC_B_IN_PASSIVO
            ,LREC_B_UA_CD_ATUALIZA
            ,LREC_B_PF_NR_ATUALIZA
            ,LREC_B_NR_TERMINAL
            ,LREC_B_NR_SECAO
            ,LREC_B_NR_VARA
            ,LREC_B_NR_ACAO_JUDICIAL
            ,LREC_B_NR_PROCESSO
            ,LREC_NR_ALVARA
            ,LREC_DT_CIENCIA
            ,LREC_DT_CREDITO
            ,LREC_DT_DEVOL
        ) VALUES (
             LEVANTAMENTO.LREC_NR_LEVANTAMENTO
            ,LEVANTAMENTO.LREC_DT_REGISTRO
            ,LEVANTAMENTO.LREC_REC_CD
            ,LEVANTAMENTO.LREC_RELE_NR_ID
            ,LEVANTAMENTO.LREC_CDLV_NR_CONTA
            ,LEVANTAMENTO.LREC_VL_AJUSTE
            ,LEVANTAMENTO.LREC_IN_PENDENCIA_UTIL
            ,LEVANTAMENTO.LREC_IN_CANCELADO
            ,LEVANTAMENTO.LREC_B_IN_PROCESSO
            ,LEVANTAMENTO.LREC_B_QT_DEPOSITO
            ,LEVANTAMENTO.LREC_B_VL_TOTAL_DEVOL_DJE
            ,LEVANTAMENTO.LREC_B_VL_TOTAL_JUROS_DJE
            ,LEVANTAMENTO.LREC_B_VL_TOTAL_TRANSF_DJE
            ,LEVANTAMENTO.LREC_B_IN_HISTORICO
            ,LEVANTAMENTO.LREC_B_IN_PASSIVO
            ,LEVANTAMENTO.LREC_B_UA_CD_ATUALIZA
            ,LEVANTAMENTO.LREC_B_PF_NR_ATUALIZA
            ,LEVANTAMENTO.LREC_B_NR_TERMINAL
            ,LEVANTAMENTO.LREC_B_NR_SECAO
            ,LEVANTAMENTO.LREC_B_NR_VARA
            ,LEVANTAMENTO.LREC_B_NR_ACAO_JUDICIAL
            ,LEVANTAMENTO.LREC_B_NR_PROCESSO
            ,LEVANTAMENTO.LREC_NR_ALVARA
            ,LEVANTAMENTO.LREC_DT_CIENCIA
            ,LEVANTAMENTO.LREC_DT_CREDITO
            ,LEVANTAMENTO.LREC_DT_DEVOL
        );

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            --DBMS_OUTPUT.PUT_LINE('Aviso: ' || LEVANTAMENTO.LREC_NR_LEVANTAMENTO || ' ja se encontra em PR_INSERE_LEVANTA_REGISTRO.');
            NULL;
        END;
    END PR_INSERE_LEVANTA_REGISTRO;


PROCEDURE PR_INSERE_LEVANTAMENTO_RECS (
     NUM_CONTA IN NUMBER
) IS
    CONTADOR     NUMBER;
    LEVANTAMENTO LEVANTAMENTO_RECS%ROWTYPE;

    BEGIN
        FOR L IN (
            SELECT
                 LREC_NR_LEVANTAMENTO
                ,LREC_DT_REGISTRO
                ,LREC_REC_CD
                ,LREC_RELE_NR_ID
                ,LREC_CDLV_NR_CONTA
                ,LREC_VL_AJUSTE
                ,LREC_IN_PENDENCIA_UTIL
                ,LREC_IN_CANCELADO
                ,LREC_B_IN_PROCESSO
                ,LREC_B_QT_DEPOSITO
                ,LREC_B_VL_TOTAL_DEVOL_DJE
                ,LREC_B_VL_TOTAL_JUROS_DJE
                ,LREC_B_VL_TOTAL_TRANSF_DJE
                ,LREC_B_IN_HISTORICO
                ,LREC_B_IN_PASSIVO
                ,LREC_B_UA_CD_ATUALIZA
                ,LREC_B_PF_NR_ATUALIZA
                ,LREC_B_NR_TERMINAL
                ,LREC_B_NR_SECAO
                ,LREC_B_NR_VARA
                ,LREC_B_NR_ACAO_JUDICIAL
                ,LREC_B_NR_PROCESSO
                ,LREC_NR_ALVARA
                ,LREC_DT_CIENCIA
                ,LREC_DT_CREDITO
                ,LREC_DT_DEVOL
            FROM
                LEVANTAMENTO_RECS@DL_POC08_EXTRATOR
            WHERE
                LREC_CDLV_NR_CONTA = NUM_CONTA
        ) LOOP
            PR_INSERE_REMESSA_LEVANTAMENTO(L.LREC_RELE_NR_ID);

            LEVANTAMENTO.LREC_NR_LEVANTAMENTO       := L.LREC_NR_LEVANTAMENTO;
            LEVANTAMENTO.LREC_DT_REGISTRO           := L.LREC_DT_REGISTRO;
            LEVANTAMENTO.LREC_REC_CD                := L.LREC_REC_CD;
            LEVANTAMENTO.LREC_RELE_NR_ID            := L.LREC_RELE_NR_ID;
            LEVANTAMENTO.LREC_CDLV_NR_CONTA         := L.LREC_CDLV_NR_CONTA;
            LEVANTAMENTO.LREC_VL_AJUSTE             := L.LREC_VL_AJUSTE;
            LEVANTAMENTO.LREC_IN_PENDENCIA_UTIL     := L.LREC_IN_PENDENCIA_UTIL;
            LEVANTAMENTO.LREC_IN_CANCELADO          := L.LREC_IN_CANCELADO;
            LEVANTAMENTO.LREC_B_IN_PROCESSO         := L.LREC_B_IN_PROCESSO;
            LEVANTAMENTO.LREC_B_QT_DEPOSITO         := L.LREC_B_QT_DEPOSITO;
            LEVANTAMENTO.LREC_B_VL_TOTAL_DEVOL_DJE  := L.LREC_B_VL_TOTAL_DEVOL_DJE;
            LEVANTAMENTO.LREC_B_VL_TOTAL_JUROS_DJE  := L.LREC_B_VL_TOTAL_JUROS_DJE;
            LEVANTAMENTO.LREC_B_VL_TOTAL_TRANSF_DJE := L.LREC_B_VL_TOTAL_TRANSF_DJE;
            LEVANTAMENTO.LREC_B_IN_HISTORICO        := L.LREC_B_IN_HISTORICO;
            LEVANTAMENTO.LREC_B_IN_PASSIVO          := L.LREC_B_IN_PASSIVO;
            LEVANTAMENTO.LREC_B_UA_CD_ATUALIZA      := L.LREC_B_UA_CD_ATUALIZA;
            LEVANTAMENTO.LREC_B_PF_NR_ATUALIZA      := L.LREC_B_PF_NR_ATUALIZA;
            LEVANTAMENTO.LREC_B_NR_TERMINAL         := L.LREC_B_NR_TERMINAL;
            LEVANTAMENTO.LREC_B_NR_SECAO            := L.LREC_B_NR_SECAO;
            LEVANTAMENTO.LREC_B_NR_VARA             := L.LREC_B_NR_VARA;
            LEVANTAMENTO.LREC_B_NR_ACAO_JUDICIAL    := L.LREC_B_NR_ACAO_JUDICIAL;
            LEVANTAMENTO.LREC_B_NR_PROCESSO         := L.LREC_B_NR_PROCESSO;
            LEVANTAMENTO.LREC_NR_ALVARA             := L.LREC_NR_ALVARA;
            LEVANTAMENTO.LREC_DT_CIENCIA            := L.LREC_DT_CIENCIA;
            LEVANTAMENTO.LREC_DT_CREDITO            := L.LREC_DT_CREDITO;
            LEVANTAMENTO.LREC_DT_DEVOL              := L.LREC_DT_DEVOL;

            PR_INSERE_LEVANTA_REGISTRO(LEVANTAMENTO);

            PR_INSERE_LEVANTA_REC_DJES(L.LREC_NR_LEVANTAMENTO);
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela LEVANTAMENTO_RECS.');

            RAISE;
        END;
    END PR_INSERE_LEVANTAMENTO_RECS;


PROCEDURE PR_INSERE_CONTA_REGISTRO (
     CONTA IN CONTA_DEP_LEVANTAS%ROWTYPE
) IS
    BEGIN
        INSERT INTO CONTA_DEP_LEVANTAS (
             CDLV_NR_CONTA
            ,CDLV_DT_REGISTRO
            ,CDLV_IN_PROCESSO
            ,CDLV_B_IN_PASSIVO
            ,CDLV_B_IN_HISTORICO
            ,CDLV_B_UA_CD_ATUALIZA
            ,CDLV_B_PF_NR_ATUALIZA
            ,CDLV_B_NR_TERMINAL
            ,CDLV_ESTB_PJ_NR
            ,CDLV_ESTB_NR
            ,CDLV_PF_NR
            ,CDLV_NR_SECAO
            ,CDLV_NR_VARA
            ,CDLV_NR_ACAO_JUDICIAL
            ,CDLV_NR_PROCESSO
            ,CDLV_IN_CONTRIB_N_IDENT
            ,CDLV_NR_CONTRIB_NIDENT
        ) VALUES (
             CONTA.CDLV_NR_CONTA
            ,CONTA.CDLV_DT_REGISTRO
            ,CONTA.CDLV_IN_PROCESSO
            ,CONTA.CDLV_B_IN_PASSIVO
            ,CONTA.CDLV_B_IN_HISTORICO
            ,CONTA.CDLV_B_UA_CD_ATUALIZA
            ,CONTA.CDLV_B_PF_NR_ATUALIZA
            ,CONTA.CDLV_B_NR_TERMINAL
            ,CONTA.CDLV_ESTB_PJ_NR
            ,CONTA.CDLV_ESTB_NR
            ,CONTA.CDLV_PF_NR
            ,CONTA.CDLV_NR_SECAO
            ,CONTA.CDLV_NR_VARA
            ,CONTA.CDLV_NR_ACAO_JUDICIAL
            ,CONTA.CDLV_NR_PROCESSO
            ,CONTA.CDLV_IN_CONTRIB_N_IDENT
            ,CONTA.CDLV_NR_CONTRIB_NIDENT
        );

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            -- DBMS_OUTPUT.PUT_LINE('Aviso: ' || CONTA.CDLV_NR_CONTA || ' ja se encontra em PR_INSERE_CONTA_REGISTRO.');
            NULL;
        END;
    END PR_INSERE_CONTA_REGISTRO;


PROCEDURE PR_INSERE_CONTA_DEP_LEVANTAS (
     CNPJ_BASE  IN NUMBER
    ,CNPJ_ORDEM IN NUMBER
    ,CPF        IN NUMBER
) IS
    CONTADOR NUMBER;
    CONTA    CONTA_DEP_LEVANTAS%ROWTYPE;

    BEGIN
        FOR CD IN (
            SELECT
                 CDLV_NR_CONTA
                ,CDLV_DT_REGISTRO
                ,CDLV_IN_PROCESSO
                ,CDLV_B_IN_PASSIVO
                ,CDLV_B_IN_HISTORICO
                ,CDLV_B_UA_CD_ATUALIZA
                ,CDLV_B_PF_NR_ATUALIZA
                ,CDLV_B_NR_TERMINAL
                ,CDLV_ESTB_PJ_NR
                ,CDLV_ESTB_NR
                ,CDLV_PF_NR
                ,CDLV_NR_SECAO
                ,CDLV_NR_VARA
                ,CDLV_NR_ACAO_JUDICIAL
                ,CDLV_NR_PROCESSO
                ,CDLV_IN_CONTRIB_N_IDENT
                ,CDLV_NR_CONTRIB_NIDENT
            FROM
                CONTA_DEP_LEVANTAS@DL_POC08_EXTRATOR
            WHERE
                (CDLV_ESTB_PJ_NR = CNPJ_BASE
                    AND CDLV_ESTB_NR = CNPJ_ORDEM)
                OR CDLV_PF_NR = CPF
        ) LOOP
            CONTA.CDLV_NR_CONTA           := CD.CDLV_NR_CONTA;
            CONTA.CDLV_DT_REGISTRO        := CD.CDLV_DT_REGISTRO;
            CONTA.CDLV_IN_PROCESSO        := CD.CDLV_IN_PROCESSO;
            CONTA.CDLV_B_IN_PASSIVO       := CD.CDLV_B_IN_PASSIVO;
            CONTA.CDLV_B_IN_HISTORICO     := CD.CDLV_B_IN_HISTORICO;
            CONTA.CDLV_B_UA_CD_ATUALIZA   := CD.CDLV_B_UA_CD_ATUALIZA;
            CONTA.CDLV_B_PF_NR_ATUALIZA   := CD.CDLV_B_PF_NR_ATUALIZA;
            CONTA.CDLV_B_NR_TERMINAL      := CD.CDLV_B_NR_TERMINAL;
            CONTA.CDLV_ESTB_PJ_NR         := CD.CDLV_ESTB_PJ_NR;
            CONTA.CDLV_ESTB_NR            := CD.CDLV_ESTB_NR;
            CONTA.CDLV_PF_NR              := CD.CDLV_PF_NR;
            CONTA.CDLV_NR_SECAO           := CD.CDLV_NR_SECAO;
            CONTA.CDLV_NR_VARA            := CD.CDLV_NR_VARA;
            CONTA.CDLV_NR_ACAO_JUDICIAL   := CD.CDLV_NR_ACAO_JUDICIAL;
            CONTA.CDLV_NR_PROCESSO        := CD.CDLV_NR_PROCESSO;
            CONTA.CDLV_IN_CONTRIB_N_IDENT := CD.CDLV_IN_CONTRIB_N_IDENT;
            CONTA.CDLV_NR_CONTRIB_NIDENT  := CD.CDLV_NR_CONTRIB_NIDENT;

            PR_INSERE_CONTA_REGISTRO(CONTA);

            PR_INSERE_LEVANTAMENTO_RECS(CD.CDLV_NR_CONTA);
        END LOOP;

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela CONTA_DEP_LEVANTAS.');

            RAISE;
        END;
    END PR_INSERE_CONTA_DEP_LEVANTAS;


-- Procedimento para insercao na tabela de pagamento
PROCEDURE PR_INSERE_PGTO (
     PAGAMENTO IN PAGAMENTOS%ROWTYPE
) IS
    BEGIN
        INSERT INTO PAGAMENTOS (
             PGTO_NR
            ,PGTO_DT_ARRECADACAO
            ,PGTO_DT_REGISTRO
            ,PGTO_IN_TP
            ,PGTO_IN_CONTRIB_N_IDENT
            ,PGTO_SITU_CD
            ,PGTO_IN_UTIL_COBRANCA
            ,PGTO_VL_TOTAL
            ,PGTO_B_REC_CD1
            ,PGTO_B_VL1
            ,PGTO_IN_IMPEDE_ALOC
            ,PGTO_IN_ORIGEM
            ,PGTO_AGEN_AGTA_CD
            ,PGTO_AGEN_CD
            ,PGTO_IN_DESMEMBRADO
            ,PGTO_B_VL_RLOCAL
            ,PGTO_B_VL_GPORTE
            ,PGTO_B_IN_INTERESSE
            ,PGTO_B_IN_PASSIVO
            ,PGTO_B_IN_ATUALIZACAO
            ,PGTO_B_DT_ATUALIZACAO
            ,PGTO_B_IN_HISTORICO
            ,PGTO_UA
            ,PGTO_ESTB_PJ_NR
            ,PGTO_ESTB_NR
            ,PGTO_OEPG_CD
            ,PGTO_DT_VENCIMENTO
            ,PGTO_NR_PERIODO_APUR
            ,PGTO_NR_PROCESSO
            ,PGTO_MCAN_CD
            ,PGTO_PC_REC_BRUT
            ,PGTO_VL_REC_BRUT_ACUM
            ,PGTO_NR_REFERENCIA
            ,PGTO_PROC_NR
            ,PGTO_NR_PARCELA
            ,PGTO_RELE_NR_SQ
            ,PGTO_PF_NR
            ,PGTO_B_NR_BDA_SQ
            ,PGTO_RPAP_NR_BDA
            ,PGTO_B_DT_RECEPCAO
            ,PGTO_DT_ENCERRA_PER_APU
            ,PGTO_ALSP_CD
            ,PGTO_MNDP_CD
            ,PGTO_B_VL2
            ,PGTO_B_VL3
            ,PGTO_B_VL4
            ,PGTO_B_VL5
            ,PGTO_B_REC_CD2
            ,PGTO_B_REC_CD3
            ,PGTO_B_REC_CD4
            ,PGTO_B_REC_CD5
            ,PGTO_B_REC_DT1
            ,PGTO_B_REC_DT2
            ,PGTO_B_REC_DT3
            ,PGTO_B_REC_DT4
            ,PGTO_B_REC_DT5
            ,PGTO_B_OEPG_DT
            ,PGTO_B_SITU_DT
            ,PGTO_B_AGEN_DT
            ,PGTO_B_MCAN_DT
            ,PGTO_B_MNDP_DT
            ,PGTO_B_ALSP_DT
            ,PGTO_B_UA_CD_ATUALIZA
            ,PGTO_B_NR_TERMINAL
            ,PGTO_B_PF_NR_ATUALIZA
            ,PGTO_B_VL_RESTITUICAO
            ,PGTO_B_VL_COMPENSACAO
            ,PGTO_B_VL_ALOCADO_RL
            ,PGTO_NR_CONTRIB_NIDENT
            ,PGTO_IN_AUTORIZA_DEB_CC
            ,PGTO_IN_MEIO_COLETA
            ,PGTO_NR_PROTOCOL_DEB_CC
            ,PGTO_NR_ID_DEPOSITO_CEF
            ,PGTO_B_VL_SALDO_PRINCIPAL
            ,PGTO_B_VL_SALDO_MULTA
            ,PGTO_B_VL_SALDO_JUROS
            ,PGTO_B_IN_REC_INVAL
            ,PGTO_B_IN_DELECAO_LOGICA
            ,PGTO_NR_DV_CONTRIB
            ,PGTO_B_DT_REGISTRO_GPORTE
            ,PGTO_NR_ID_AR
            ,PGTO_IN_INCONSISTENCIA
            ,PGTO_NR_REMESSA
            ,PGTO_SEAR_TMAR_CD
            ,PGTO_SEAR_TPR_CD
            ,PGTO_SEAR_TSP_CD
            ,PGTO_SEAR_TAUD_CD
            ,PGTO_SEAR_TDA_CD
            ,PGTO_TNR_CD
            ,PGTO_DT_LIMITE_PGTO_DEB
            ,PGTO_DT_RECEP_REG_EM_AG
            ,PGTO_IN_MEIO_RECEP_INF
            ,PGTO_NR_CTRL_RECEP_INF
            ,PGTO_IN_COND_SIT_REG
            ,PGTO_IN_MOD_ARREC
            ,PGTO_DT_APROPRIACAO
            ,PGTO_DT_PERIODO_APROPRIA
            ,PGTO_QT_DOC_AGREGADO
            ,PGTO_TIIC_CD
            ,PGTO_DOVR_NR_SQ
            ,PGTO_TSGI_CD
            ,PGTO_TQRP_CD
            ,PGTO_TBFR_CD
            ,PGTO_TPSI_CD
            ,PGTO_UA_DT_PGTO
            ,PGTO_UA_CLASS_CD
            ,PGTO_MUNIC_CLASS_CD
            ,PGTO_B_CD_MUN_DOM_CONTRIB
            ,PGTO_TIFP_CD
            ,PGTO_B_IN_CLASS_PRINC
            ,PGTO_B_IN_CLASS_MULTA
            ,PGTO_B_IN_CLASS_JUROS
            ,PGTO_IN_MEIO_RECEP_PG_RF
            ,PGTO_NR_CTRL_RECEP_PG_RF
            ,PGTO_DT_GER_REG_SIST_ORIG
            ,PGTO_IN_ORIGEM_AR
            ,PGTO_B_VL_DJE_SUNL
            ,PGTO_B_VL_DJE_SDNL
            ,PGTO_B_VL_DJE_SUTD
            ,PGTO_B_VL_DJE_SDTD
            ,PGTO_B_VL_DJE_VTDC
            ,PGTO_NIT_NI
            ,PGTO_PGTO_NR_ID_AR
            ,PGTO_B_IP_TERMINAL
            ,PGTO_IN_DARA
         --   ,PGTO_B_IN_ALOCADO
        ) VALUES (
             PAGAMENTO.PGTO_NR
            ,PAGAMENTO.PGTO_DT_ARRECADACAO
            ,PAGAMENTO.PGTO_DT_REGISTRO
            ,PAGAMENTO.PGTO_IN_TP
            ,PAGAMENTO.PGTO_IN_CONTRIB_N_IDENT
            ,PAGAMENTO.PGTO_SITU_CD
            ,PAGAMENTO.PGTO_IN_UTIL_COBRANCA
            ,PAGAMENTO.PGTO_VL_TOTAL
            ,PAGAMENTO.PGTO_B_REC_CD1
            ,PAGAMENTO.PGTO_B_VL1
            ,PAGAMENTO.PGTO_IN_IMPEDE_ALOC
            ,PAGAMENTO.PGTO_IN_ORIGEM
            ,PAGAMENTO.PGTO_AGEN_AGTA_CD
            ,PAGAMENTO.PGTO_AGEN_CD
            ,PAGAMENTO.PGTO_IN_DESMEMBRADO
            ,PAGAMENTO.PGTO_B_VL_RLOCAL
            ,PAGAMENTO.PGTO_B_VL_GPORTE
            ,PAGAMENTO.PGTO_B_IN_INTERESSE
            ,PAGAMENTO.PGTO_B_IN_PASSIVO
            ,PAGAMENTO.PGTO_B_IN_ATUALIZACAO
            ,PAGAMENTO.PGTO_B_DT_ATUALIZACAO
            ,PAGAMENTO.PGTO_B_IN_HISTORICO
            ,PAGAMENTO.PGTO_UA
            ,PAGAMENTO.PGTO_ESTB_PJ_NR
            ,PAGAMENTO.PGTO_ESTB_NR
            ,PAGAMENTO.PGTO_OEPG_CD
            ,PAGAMENTO.PGTO_DT_VENCIMENTO
            ,PAGAMENTO.PGTO_NR_PERIODO_APUR
            ,PAGAMENTO.PGTO_NR_PROCESSO
            ,PAGAMENTO.PGTO_MCAN_CD
            ,PAGAMENTO.PGTO_PC_REC_BRUT
            ,PAGAMENTO.PGTO_VL_REC_BRUT_ACUM
            ,PAGAMENTO.PGTO_NR_REFERENCIA
            ,PAGAMENTO.PGTO_PROC_NR
            ,PAGAMENTO.PGTO_NR_PARCELA
            ,PAGAMENTO.PGTO_RELE_NR_SQ
            ,PAGAMENTO.PGTO_PF_NR
            ,PAGAMENTO.PGTO_B_NR_BDA_SQ
            ,PAGAMENTO.PGTO_RPAP_NR_BDA
            ,PAGAMENTO.PGTO_B_DT_RECEPCAO
            ,PAGAMENTO.PGTO_DT_ENCERRA_PER_APU
            ,PAGAMENTO.PGTO_ALSP_CD
            ,PAGAMENTO.PGTO_MNDP_CD
            ,PAGAMENTO.PGTO_B_VL2
            ,PAGAMENTO.PGTO_B_VL3
            ,PAGAMENTO.PGTO_B_VL4
            ,PAGAMENTO.PGTO_B_VL5
            ,PAGAMENTO.PGTO_B_REC_CD2
            ,PAGAMENTO.PGTO_B_REC_CD3
            ,PAGAMENTO.PGTO_B_REC_CD4
            ,PAGAMENTO.PGTO_B_REC_CD5
            ,PAGAMENTO.PGTO_B_REC_DT1
            ,PAGAMENTO.PGTO_B_REC_DT2
            ,PAGAMENTO.PGTO_B_REC_DT3
            ,PAGAMENTO.PGTO_B_REC_DT4
            ,PAGAMENTO.PGTO_B_REC_DT5
            ,PAGAMENTO.PGTO_B_OEPG_DT
            ,PAGAMENTO.PGTO_B_SITU_DT
            ,PAGAMENTO.PGTO_B_AGEN_DT
            ,PAGAMENTO.PGTO_B_MCAN_DT
            ,PAGAMENTO.PGTO_B_MNDP_DT
            ,PAGAMENTO.PGTO_B_ALSP_DT
            ,PAGAMENTO.PGTO_B_UA_CD_ATUALIZA
            ,PAGAMENTO.PGTO_B_NR_TERMINAL
            ,PAGAMENTO.PGTO_B_PF_NR_ATUALIZA
            ,PAGAMENTO.PGTO_B_VL_RESTITUICAO
            ,PAGAMENTO.PGTO_B_VL_COMPENSACAO
            ,PAGAMENTO.PGTO_B_VL_ALOCADO_RL
            ,PAGAMENTO.PGTO_NR_CONTRIB_NIDENT
            ,PAGAMENTO.PGTO_IN_AUTORIZA_DEB_CC
            ,PAGAMENTO.PGTO_IN_MEIO_COLETA
            ,PAGAMENTO.PGTO_NR_PROTOCOL_DEB_CC
            ,PAGAMENTO.PGTO_NR_ID_DEPOSITO_CEF
            ,PAGAMENTO.PGTO_B_VL_SALDO_PRINCIPAL
            ,PAGAMENTO.PGTO_B_VL_SALDO_MULTA
            ,PAGAMENTO.PGTO_B_VL_SALDO_JUROS
            ,PAGAMENTO.PGTO_B_IN_REC_INVAL
            ,PAGAMENTO.PGTO_B_IN_DELECAO_LOGICA
            ,PAGAMENTO.PGTO_NR_DV_CONTRIB
            ,PAGAMENTO.PGTO_B_DT_REGISTRO_GPORTE
            ,PAGAMENTO.PGTO_NR_ID_AR
            ,PAGAMENTO.PGTO_IN_INCONSISTENCIA
            ,PAGAMENTO.PGTO_NR_REMESSA
            ,PAGAMENTO.PGTO_SEAR_TMAR_CD
            ,PAGAMENTO.PGTO_SEAR_TPR_CD
            ,PAGAMENTO.PGTO_SEAR_TSP_CD
            ,PAGAMENTO.PGTO_SEAR_TAUD_CD
            ,PAGAMENTO.PGTO_SEAR_TDA_CD
            ,PAGAMENTO.PGTO_TNR_CD
            ,PAGAMENTO.PGTO_DT_LIMITE_PGTO_DEB
            ,PAGAMENTO.PGTO_DT_RECEP_REG_EM_AG
            ,PAGAMENTO.PGTO_IN_MEIO_RECEP_INF
            ,PAGAMENTO.PGTO_NR_CTRL_RECEP_INF
            ,PAGAMENTO.PGTO_IN_COND_SIT_REG
            ,PAGAMENTO.PGTO_IN_MOD_ARREC
            ,PAGAMENTO.PGTO_DT_APROPRIACAO
            ,PAGAMENTO.PGTO_DT_PERIODO_APROPRIA
            ,PAGAMENTO.PGTO_QT_DOC_AGREGADO
            ,PAGAMENTO.PGTO_TIIC_CD
            ,PAGAMENTO.PGTO_DOVR_NR_SQ
            ,PAGAMENTO.PGTO_TSGI_CD
            ,PAGAMENTO.PGTO_TQRP_CD
            ,PAGAMENTO.PGTO_TBFR_CD
            ,PAGAMENTO.PGTO_TPSI_CD
            ,PAGAMENTO.PGTO_UA_DT_PGTO
            ,PAGAMENTO.PGTO_UA_CLASS_CD
            ,PAGAMENTO.PGTO_MUNIC_CLASS_CD
            ,PAGAMENTO.PGTO_B_CD_MUN_DOM_CONTRIB
            ,PAGAMENTO.PGTO_TIFP_CD
            ,PAGAMENTO.PGTO_B_IN_CLASS_PRINC
            ,PAGAMENTO.PGTO_B_IN_CLASS_MULTA
            ,PAGAMENTO.PGTO_B_IN_CLASS_JUROS
            ,PAGAMENTO.PGTO_IN_MEIO_RECEP_PG_RF
            ,PAGAMENTO.PGTO_NR_CTRL_RECEP_PG_RF
            ,PAGAMENTO.PGTO_DT_GER_REG_SIST_ORIG
            ,PAGAMENTO.PGTO_IN_ORIGEM_AR
            ,PAGAMENTO.PGTO_B_VL_DJE_SUNL
            ,PAGAMENTO.PGTO_B_VL_DJE_SDNL
            ,PAGAMENTO.PGTO_B_VL_DJE_SUTD
            ,PAGAMENTO.PGTO_B_VL_DJE_SDTD
            ,PAGAMENTO.PGTO_B_VL_DJE_VTDC
            ,PAGAMENTO.PGTO_NIT_NI
            ,PAGAMENTO.PGTO_PGTO_NR_ID_AR
            ,PAGAMENTO.PGTO_B_IP_TERMINAL
            ,PAGAMENTO.PGTO_IN_DARA
          --  ,PAGAMENTO.PGTO_B_IN_ALOCADO
        );

        PR_INSERE_HISTORICO_PGTO(PAGAMENTO.PGTO_NR);

        PR_INSERE_RESTIT_PGTO(PAGAMENTO.PGTO_NR);

        PR_INSERE_BLOQUEIOS_PGTO(PAGAMENTO.PGTO_NR);

        IF PAGAMENTO.PGTO_IN_TP = '7' OR PAGAMENTO.PGTO_SEAR_TDA_CD = 7 THEN
            PR_INSERE_OPER_CONTABIL(PAGAMENTO.PGTO_NR_ID_AR);
        END IF;

        IF (PAGAMENTO.PGTO_IN_COND_SIT_REG IS NOT NULL) THEN

            PR_INSERE_COMPLEMENTO(NULL, PAGAMENTO.PGTO_NR_ID_AR);

            PR_INSERE_DESMEMBRAMENTO(PAGAMENTO.PGTO_NR, PAGAMENTO.PGTO_NR_ID_AR);

            PR_INSERE_OPER_CONTABIL(PAGAMENTO.PGTO_NR_ID_AR);

            PR_INSERE_CLASSIFICACAO(PAGAMENTO.PGTO_NR, PAGAMENTO.PGTO_NR_ID_AR);

            PR_INSERE_INCONSISTENCIA(PAGAMENTO.PGTO_NR_ID_AR);
        END IF;

        COMMIT;

        EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
        BEGIN
            -- DBMS_OUTPUT.PUT_LINE('Aviso: ' || PAGAMENTO.PGTO_NR || ' ja se encontra em PR_INSERE_PGTO.');
            NULL;
        END;

        WHEN ERRO_DOC_EXISTE THEN
        BEGIN
            ROLLBACK;
            RAISE ERRO_DOC_EXISTE;
        END;

        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro incluindo registro na tabela PAGAMENTOS.');

            RAISE;
        END;
    END PR_INSERE_PGTO;


FUNCTION FC_VERIFICA_DEPENDENCIA (
     SEQUENCIAL_EXTRACAO IN NUMBER
    ,TIPO_OPERACAO       IN VARCHAR2
    ,TIPO_CONTRIBUINTE   IN VARCHAR2
    ,CONTRIBUINTE        IN VARCHAR2
) RETURN NUMBER IS
    COMANDO1 VARCHAR2(1000);
    COMANDO2 VARCHAR2(1000);
    TYPE TIPO_CURSOR_DEPENDENCIA IS REF CURSOR;
    CURSOR_DEPENDENCIA TIPO_CURSOR_DEPENDENCIA;
    DEPENDENCIA VARCHAR2(4);
    MODULO NUMBER;
    QTD_MODULO NUMBER;

    BEGIN
        QTD_MODULO := 0;

        IF (TIPO_OPERACAO = 'I') THEN
            COMANDO1 := '
                SELECT
                    DISTINCT ORED_NM_EXTRATOR_GER
                FROM
                    ORDEM_EXEC_DEMANDADAS_APO
                WHERE
                    TO_NUMBER(ORED_ORDEM_INCLUSAO) < (
                        SELECT
                            DISTINCT TO_NUMBER(ORED_ORDEM_INCLUSAO)
                        FROM
                            ORDEM_EXEC_DEMANDADAS_APO
                        WHERE
                            ORED_NM_EXTRATOR_GER = ''PGTO'')';
        ELSE
            COMANDO1 := '
                SELECT
                    DISTINCT ORED_NM_EXTRATOR_GER
                FROM
                    ORDEM_EXEC_DEMANDADAS_APO
                WHERE
                    TO_NUMBER(ORED_ORDEM_EXCLUSAO) < (
                        SELECT
                            DISTINCT TO_NUMBER(ORED_ORDEM_EXCLUSAO)
                        FROM
                            ORDEM_EXEC_DEMANDADAS_APO
                        WHERE
                            ORED_NM_EXTRATOR_GER = ''PGTO'')';
        END IF;



        OPEN CURSOR_DEPENDENCIA FOR COMANDO1;
            LOOP
                FETCH CURSOR_DEPENDENCIA INTO DEPENDENCIA;
                EXIT WHEN CURSOR_DEPENDENCIA %NOTFOUND;
                    BEGIN
                        COMANDO2 := '
                            SELECT
                                COUNT(*)
                            FROM
                                CONTRIBUINTE_HOM_TRE_APO
                            WHERE
                                COHT_INHT_NR_SEQUENCIAL  = ' || SEQUENCIAL_EXTRACAO || '
                                AND COHT_IN_OPERACAO     = ''' || TIPO_OPERACAO || '''
                                AND COHT_IN_CONTRIBUINTE = ''' || TIPO_CONTRIBUINTE || '''
                                AND COHT_NR_NI           = ' || CONTRIBUINTE || '
                                AND COHT_IN_' || DEPENDENCIA || ' NOT IN (''S'', ''0'', ''X'')';

                        EXECUTE IMMEDIATE COMANDO2 INTO MODULO;

                        IF (MODULO <> 0) THEN
                            QTD_MODULO := QTD_MODULO + 1;
                        END IF;
                    END;
            END LOOP;
        CLOSE CURSOR_DEPENDENCIA;

        RETURN QTD_MODULO;
    END FC_VERIFICA_DEPENDENCIA;


PROCEDURE PR_DESLIGA_REGRA_PGTO IS
    COMANDO VARCHAR2(2500);

    BEGIN
        DBMS_OUTPUT.PUT_LINE('Desligando regras de banco do modulo PGTO.');

        -- Desabilitando as constraints das tabelas afetadas pelo extrator
        -- As constraints do tipo chave primaria nao serao afetadas
        FOR CON IN (
            SELECT
                TABLE_NAME,
                CONSTRAINT_NAME
            FROM
                USER_CONSTRAINTS
            WHERE
                CONSTRAINT_TYPE <> 'P'
                AND TABLE_NAME IN (
                    SELECT
                        OBJECT_NAME
                    FROM
                        DBA_OBJECTS
                    WHERE
                        OWNER = 'PGTO'
                        AND OBJECT_TYPE = 'TABLE'
                )
        ) LOOP
            COMANDO := 'ALTER TABLE ' || CON.TABLE_NAME || ' DISABLE CONSTRAINT ' || CON.CONSTRAINT_NAME;
            EXECUTE IMMEDIATE COMANDO;
        END LOOP;

        -- Desabilitando as triggers do banco
        FOR TRI IN (
                SELECT
                    TRIGGER_NAME
                FROM
                    USER_TRIGGERS
                WHERE
                    TABLE_NAME IN (
                        SELECT
                            OBJECT_NAME
                        FROM
                            DBA_OBJECTS
                        WHERE
                            OWNER = 'PGTO'
                            AND OBJECT_TYPE = 'TABLE'
                    )
        ) LOOP
            COMANDO := 'ALTER TRIGGER ' || TRI.TRIGGER_NAME || ' DISABLE';
            EXECUTE IMMEDIATE COMANDO;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('SIEF STATUS 00: Regras do modulo PGTO desligadas com sucesso.');

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro ao executar o comando: ' || COMANDO);
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 3 - ERRO: ' || SUBSTR(SQLERRM, 1, 200));

            RAISE;
        END;
    END PR_DESLIGA_REGRA_PGTO;


PROCEDURE PR_LIGA_REGRA_PGTO IS
    COMANDO VARCHAR2(2500);

    BEGIN
        DBMS_OUTPUT.PUT_LINE('Ligando regras de banco do modulo PGTO.');

        -- Habilitando as constraints das tabelas afetadas pelo extrator
        -- As constraints do tipo chave primaria nao serao afetadas
        FOR CON IN (
            SELECT
                TABLE_NAME,
                CONSTRAINT_NAME
            FROM
                USER_CONSTRAINTS
            WHERE
                CONSTRAINT_TYPE <> 'P' and CONSTRAINT_NAME not in ('OCPG_ALOC_FK','PGTO_ESTB_FK')
                AND TABLE_NAME IN (
                    SELECT
                        OBJECT_NAME
                    FROM
                        DBA_OBJECTS
                    WHERE
                        OWNER = 'PGTO'
                        AND OBJECT_TYPE = 'TABLE')
                AND TABLE_NAME NOT IN ('ENVIA_EMAIL_CAN_ALOC_APO')
        ) LOOP
            COMANDO := 'ALTER TABLE ' || CON.TABLE_NAME || ' ENABLE NOVALIDATE CONSTRAINT ' || CON.CONSTRAINT_NAME;
            EXECUTE IMMEDIATE COMANDO;
        END LOOP;

        -- Habilitando as triggers do banco
        FOR TRI IN (
                SELECT
                    TRIGGER_NAME
                FROM
                    USER_TRIGGERS
                WHERE
                    TABLE_NAME IN (
                        SELECT
                            OBJECT_NAME
                        FROM
                            DBA_OBJECTS
                        WHERE
                            OWNER = 'PGTO'
                            AND OBJECT_TYPE = 'TABLE'
                    )
        ) LOOP
            COMANDO := 'ALTER TRIGGER ' || TRI.TRIGGER_NAME || ' ENABLE';
            EXECUTE IMMEDIATE COMANDO;
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('SIEF STATUS 00: Regras do modulo PGTO ligadas com sucesso.');

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            DBMS_OUTPUT.PUT_LINE('Erro ao executar o comando: ' || COMANDO);
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 3 - ERRO: ' || SUBSTR(SQLERRM, 1, 200));

            RAISE;
        END;
    END PR_LIGA_REGRA_PGTO;


PROCEDURE PR_EXCLUIR_PGTO (
     SEQUENCIAL IN  NUMBER
    ,TIPO       IN  VARCHAR2
    ,CRF        IN  NUMBER
    ,COD_ERR    OUT NUMBER
    ,MSG_ERR    OUT VARCHAR2
) IS
    CRF_CHAR     VARCHAR2(14);
    IND_PROC     VARCHAR2(1);
    DT_INI       DATE;
    DT_FIM       DATE;
    NI           CONTRIBUINTE_HOM_TRE_APO%ROWTYPE;
    ORDEM        ORDEM_EXEC_DEMANDADAS_APO%ROWTYPE;
    PAGAMENTO    PAGAMENTOS%ROWTYPE;
    QTD_PGTO     NUMBER;
    QTD_MODULO   NUMBER;

    BEGIN
        COD_ERR    := 0;
        MSG_ERR    := NULL;
        CRF_CHAR   := NULL;
        IND_PROC   := 'B';
        DT_INI     := SYSDATE;
        QTD_MODULO := 0;

        -- Evita criacao de historico desnecessario
        PK_PGTO_00.WG_OPC_ACAO := 'A';

        /**
        Caso o tipo tenha valor 'F', NI deve conter os 11 ultimos digitos
        do parametro CRF. Caso o tipo tenha valor 'J' NI deve conter
        os 14 ultimos digitos do parametro CRF.
        **/
        IF TIPO = 'F' THEN
            CRF_CHAR := LPAD(TO_CHAR(CRF), 11, '0'); -- Pessoa Fisica
        ELSIF TIPO = 'J' THEN
            CRF_CHAR  := LPAD(TO_CHAR(CRF), 14, '0'); -- Pessoa Juridica
        END IF; -- END de "IF TIPO = 'F' THEN"

        DBMS_OUTPUT.PUT_LINE('Inicializando exclusao de documentos para o NI: ' || CRF || TIPO);

        BEGIN
            SELECT * INTO NI
            FROM
                CONTRIBUINTE_HOM_TRE_APO
            WHERE
                COHT_INHT_NR_SEQUENCIAL = SEQUENCIAL
                AND COHT_IN_PGTO IN ('E')
                AND COHT_NR_NI = TO_NUMBER(CRF_CHAR)
                AND COHT_IN_CONTRIBUINTE = TIPO;

            EXCEPTION
            WHEN NO_DATA_FOUND THEN
            BEGIN
                DBMS_OUTPUT.PUT_LINE('Nao existe linha na tabela CONTRIBUINTE_HOM_TRE_APO que satisfaca as condicoes');
                DBMS_OUTPUT.PUT_LINE('COHT_INHT_NR_SEQUENCIAL = ' || SEQUENCIAL);
                DBMS_OUTPUT.PUT_LINE('COHT_IN_PGTO = E');
                DBMS_OUTPUT.PUT_LINE('COHT_NR_NI = ' || TO_NUMBER(CRF_CHAR));
                DBMS_OUTPUT.PUT_LINE('COHT_IN_CONTRIBUINTE = ' || TIPO);

                RAISE;
            END;
        END;

        -- Colocando erro no campo de status, para o caso do processamento ser
        -- interrompido abruptamente.
        UPDATE
            CONTRIBUINTE_HOM_TRE_APO
        SET
             COHT_IN_PGTO      = 'N'
        WHERE
            COHT_INHT_NR_SEQUENCIAL  = NI.COHT_INHT_NR_SEQUENCIAL
            AND COHT_IN_OPERACAO     = 'E'
            AND COHT_IN_CONTRIBUINTE = TIPO
            AND COHT_NR_NI           = CRF;

        COMMIT;

        SELECT
            COUNT(*)
        INTO
            QTD_PGTO
        FROM
            PAGAMENTOS
        WHERE
            (PGTO_ESTB_PJ_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 1, 8))
                AND PGTO_ESTB_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 9, 4)) AND TIPO = 'J')
            OR (PGTO_PF_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 1, 9)) AND TIPO = 'F');

        QTD_MODULO := FC_VERIFICA_DEPENDENCIA(
            NI.COHT_INHT_NR_SEQUENCIAL,
            'E',
            TIPO,
            CRF
        );

        IF (QTD_MODULO <= 0) THEN
            PR_EXCLUI_CONTA_DEP_LEVANTAS(TO_NUMBER(SUBSTR(CRF_CHAR, 1, 8)),
                TO_NUMBER(SUBSTR(CRF_CHAR, 9, 4)), TO_NUMBER(SUBSTR(CRF_CHAR, 1, 9)));

            FOR P IN (
                    SELECT *
                    FROM
                        PAGAMENTOS
                    WHERE
                        ( PGTO_ESTB_PJ_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 1, 8))
                            AND PGTO_ESTB_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 9, 4)) AND TIPO = 'J' )
                        OR ( PGTO_PF_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 1, 9)) AND TIPO = 'F' )
            ) LOOP -- LOOP na tabela PAGAMENTOS
                BEGIN
                    PR_EXCLUI_PGTO(P);

                    EXCEPTION
                    WHEN OTHERS THEN
                    BEGIN
                        COD_ERR  := SQLCODE;
                        MSG_ERR  := SUBSTR(SQLERRM, 1, 200);
                        IND_PROC := 'N';

                        DBMS_OUTPUT.PUT_LINE('Documento: ' || TO_CHAR(P.PGTO_NR));
                        DBMS_OUTPUT.PUT_LINE(SUBSTR('ERRO: ' || MSG_ERR, 1, 200));

                        ROLLBACK;
                    END;
                END;
            END LOOP;
        ELSE
            COD_ERR := -20001;
            MSG_ERR := 'Existem modulos(' || QTD_MODULO || ') predecessores na exclusao que terminaram com valores diferentes de S, 0, X';
        END IF;

        IF (QTD_PGTO = 0 AND COD_ERR = 0) THEN
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 00: Nao existem documentos do NI ' || CRF_CHAR || TIPO || ' para serem excluidos.');
            IND_PROC := '0';
        ELSIF (QTD_PGTO > 0 AND COD_ERR = 0) THEN
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 00: Os documentos do NI ' || CRF_CHAR || TIPO || ' foram excluidos com sucesso.');
            IND_PROC := 'S';
        ELSE
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 3 - ERRO: ' || SUBSTR(MSG_ERR, 1, 200));
            DBMS_OUTPUT.PUT_LINE('ERRO: Ocorreram erros durante a exclusao dos documentos do NI ' || CRF_CHAR || TIPO);
            IND_PROC := 'N';
        END IF;

        -- Atualizando o indicador de processamento
        UPDATE
            CONTRIBUINTE_HOM_TRE_APO
        SET
             COHT_IN_PGTO      = IND_PROC
            ,COHT_COD_ERRO_ORA = COD_ERR
        WHERE
            COHT_INHT_NR_SEQUENCIAL  = NI.COHT_INHT_NR_SEQUENCIAL
            AND COHT_IN_OPERACAO     = 'E'
            AND COHT_IN_CONTRIBUINTE = TIPO
            AND COHT_NR_NI           = CRF;

        COMMIT;

        DT_FIM := SYSDATE;

        DBMS_OUTPUT.PUT_LINE('Inicio PR_EXCLUIR_PGTO: ' || TO_CHAR(DT_INI, 'dd/mm/yyyy hh24:mi:ss'));
        DBMS_OUTPUT.PUT_LINE('Fim    PR_EXCLUIR_PGTO: ' || TO_CHAR(DT_FIM, 'dd/mm/yyyy hh24:mi:ss'));

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            COD_ERR := SQLCODE;
            MSG_ERR := SUBSTR(SQLERRM, 1, 200);

            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 3 - ERRO: ' || SUBSTR(MSG_ERR, 1, 200));
            DBMS_OUTPUT.PUT_LINE('Contribuinte: ' || CRF || TIPO);

            IND_PROC := 'N';

            UPDATE
                CONTRIBUINTE_HOM_TRE_APO
            SET
                 COHT_IN_PGTO      = IND_PROC
                ,COHT_COD_ERRO_ORA = COD_ERR
            WHERE
                COHT_INHT_NR_SEQUENCIAL  = NI.COHT_INHT_NR_SEQUENCIAL
                AND COHT_IN_OPERACAO     = 'E'
                AND COHT_IN_CONTRIBUINTE = TIPO
                AND COHT_NR_NI           = CRF;

            COMMIT;
        END;
    END PR_EXCLUIR_PGTO;


-- Corpo do procedimento de extracao de dados.
PROCEDURE PR_INCLUIR_PGTO (
     SEQUENCIAL IN  NUMBER
    ,TIPO       IN  VARCHAR2
    ,CRF        IN  NUMBER
    ,COD_ERR    OUT NUMBER
    ,MSG_ERR    OUT VARCHAR2
) IS
    TYPE T_CURSOR_PAGAMENTO IS REF CURSOR;
    CURSOR_PAGAMENTO      T_CURSOR_PAGAMENTO;

    CRF_CHAR     VARCHAR2(14);
    IND_PROC     VARCHAR2(1);
    IND_PROC_ANT VARCHAR2(1);
    DT_INI       DATE;
    DT_FIM       DATE;
    NI           CONTRIBUINTE_HOM_TRE_APO%ROWTYPE;
    ORDEM        ORDEM_EXEC_DEMANDADAS_APO%ROWTYPE;
    PAGAMENTO    PAGAMENTOS%ROWTYPE;
    QTD_PGTO     NUMBER;
    QTD_MODULO   NUMBER;
    COMANDO      VARCHAR2(2500);

    BEGIN
        COD_ERR    := 0;
        MSG_ERR    := NULL;
        CRF_CHAR   := NULL;
        IND_PROC   := 'B';
        DT_INI     := SYSDATE;
        QTD_PGTO   := 0;
        QTD_MODULO := 0;

        -- Evita criacao de historico desnecessario
        PK_PGTO_00.WG_OPC_ACAO := 'A';

        /**
        Caso o tipo tenha valor 'F', NI deve conter os 11 ultimos digitos
        do parametro CRF. Caso o tipo tenha valor 'J' NI deve conter
        os 14 ultimos digitos do parametro CRF.
        **/
        IF TIPO = 'F' THEN
            CRF_CHAR := LPAD(TO_CHAR(CRF), 11, '0'); -- Pessoa Fisica
        ELSIF TIPO = 'J' THEN
            CRF_CHAR  := LPAD(TO_CHAR(CRF), 14, '0'); -- Pessoa Juridica
        END IF;

        DBMS_OUTPUT.PUT_LINE('Inicializando inclusao de documentos para o NI: ' || CRF || TIPO);

        BEGIN
            SELECT * INTO NI
            FROM
                CONTRIBUINTE_HOM_TRE_APO
            WHERE
                COHT_INHT_NR_SEQUENCIAL = SEQUENCIAL
                AND COHT_IN_PGTO IN ('I')
                AND COHT_NR_NI = TO_NUMBER(CRF_CHAR)
                AND COHT_IN_CONTRIBUINTE = TIPO;

            EXCEPTION
            WHEN NO_DATA_FOUND THEN
            BEGIN
                DBMS_OUTPUT.PUT_LINE('Nao existe linha na tabela CONTRIBUINTE_HOM_TRE_APO que satisfaca as condicoes');
                DBMS_OUTPUT.PUT_LINE('COHT_INHT_NR_SEQUENCIAL = ' || SEQUENCIAL);
                DBMS_OUTPUT.PUT_LINE('COHT_IN_PGTO = I');
                DBMS_OUTPUT.PUT_LINE('COHT_NR_NI = ' || TO_NUMBER(CRF_CHAR));
                DBMS_OUTPUT.PUT_LINE('COHT_IN_CONTRIBUINTE = ' || TIPO);

                RAISE;
            END;
        END;

        -- Colando erro no campo de status, para o caso do processamento ser
        -- interrompido abruptamente.
        UPDATE
            CONTRIBUINTE_HOM_TRE_APO
        SET
             COHT_IN_PGTO = 'N'
        WHERE
            COHT_INHT_NR_SEQUENCIAL  = NI.COHT_INHT_NR_SEQUENCIAL
            AND COHT_IN_OPERACAO     = 'I'
            AND COHT_IN_CONTRIBUINTE = TIPO
            AND COHT_NR_NI           = CRF;

        COMMIT;

        QTD_MODULO := FC_VERIFICA_DEPENDENCIA(
            NI.COHT_INHT_NR_SEQUENCIAL,
            'I ',
            TIPO,
            CRF
        );

        IF (QTD_MODULO <= 0) THEN
            PR_INSERE_CONTA_DEP_LEVANTAS(TO_NUMBER(SUBSTR(CRF_CHAR, 1, 8)),
                TO_NUMBER(SUBSTR(CRF_CHAR, 9, 4)), TO_NUMBER(SUBSTR(CRF_CHAR, 1, 9)));

            FOR P IN (
                SELECT
                     PGTO_NR
                    ,PGTO_DT_ARRECADACAO
                    ,PGTO_DT_REGISTRO
                    ,PGTO_IN_TP
                    ,PGTO_IN_CONTRIB_N_IDENT
                    ,PGTO_SITU_CD
                    ,PGTO_IN_UTIL_COBRANCA
                    ,PGTO_VL_TOTAL
                    ,PGTO_B_REC_CD1
                    ,PGTO_B_VL1
                    ,PGTO_IN_IMPEDE_ALOC
                    ,PGTO_IN_ORIGEM
                    ,PGTO_AGEN_AGTA_CD
                    ,PGTO_AGEN_CD
                    ,PGTO_IN_DESMEMBRADO
                    ,PGTO_B_VL_RLOCAL
                    ,PGTO_B_VL_GPORTE
                    ,PGTO_B_IN_INTERESSE
                    ,PGTO_B_IN_PASSIVO
                    ,PGTO_B_IN_ATUALIZACAO
                    ,PGTO_B_DT_ATUALIZACAO
                    ,PGTO_B_IN_HISTORICO
                    ,PGTO_UA
                    ,PGTO_ESTB_PJ_NR
                    ,PGTO_ESTB_NR
                    ,PGTO_OEPG_CD
                    ,PGTO_DT_VENCIMENTO
                    ,PGTO_NR_PERIODO_APUR
                    ,PGTO_NR_PROCESSO
                    ,PGTO_MCAN_CD
                    ,PGTO_PC_REC_BRUT
                    ,PGTO_VL_REC_BRUT_ACUM
                    ,PGTO_NR_REFERENCIA
                    ,PGTO_PROC_NR
                    ,PGTO_NR_PARCELA
                    ,PGTO_RELE_NR_SQ
                    ,PGTO_PF_NR
                    ,PGTO_B_NR_BDA_SQ
                    ,PGTO_RPAP_NR_BDA
                    ,PGTO_B_DT_RECEPCAO
                    ,PGTO_DT_ENCERRA_PER_APU
                    ,PGTO_ALSP_CD
                    ,PGTO_MNDP_CD
                    ,PGTO_B_VL2
                    ,PGTO_B_VL3
                    ,PGTO_B_VL4
                    ,PGTO_B_VL5
                    ,PGTO_B_REC_CD2
                    ,PGTO_B_REC_CD3
                    ,PGTO_B_REC_CD4
                    ,PGTO_B_REC_CD5
                    ,PGTO_B_REC_DT1
                    ,PGTO_B_REC_DT2
                    ,PGTO_B_REC_DT3
                    ,PGTO_B_REC_DT4
                    ,PGTO_B_REC_DT5
                    ,PGTO_B_OEPG_DT
                    ,PGTO_B_SITU_DT
                    ,PGTO_B_AGEN_DT
                    ,PGTO_B_MCAN_DT
                    ,PGTO_B_MNDP_DT
                    ,PGTO_B_ALSP_DT
                    ,PGTO_B_UA_CD_ATUALIZA
                    ,PGTO_B_NR_TERMINAL
                    ,PGTO_B_PF_NR_ATUALIZA
                    ,PGTO_B_VL_RESTITUICAO
                    ,PGTO_B_VL_COMPENSACAO
                    ,PGTO_B_VL_ALOCADO_RL
                    ,PGTO_NR_CONTRIB_NIDENT
                    ,PGTO_IN_AUTORIZA_DEB_CC
                    ,PGTO_IN_MEIO_COLETA
                    ,PGTO_NR_PROTOCOL_DEB_CC
                    ,PGTO_NR_ID_DEPOSITO_CEF
                    ,PGTO_B_VL_SALDO_PRINCIPAL
                    ,PGTO_B_VL_SALDO_MULTA
                    ,PGTO_B_VL_SALDO_JUROS
                    ,PGTO_B_IN_REC_INVAL
                    ,PGTO_B_IN_DELECAO_LOGICA
                    ,PGTO_NR_DV_CONTRIB
                    ,PGTO_B_DT_REGISTRO_GPORTE
                    ,PGTO_NR_ID_AR
                    ,PGTO_IN_INCONSISTENCIA
                    ,PGTO_NR_REMESSA
                    ,PGTO_SEAR_TMAR_CD
                    ,PGTO_SEAR_TPR_CD
                    ,PGTO_SEAR_TSP_CD
                    ,PGTO_SEAR_TAUD_CD
                    ,PGTO_SEAR_TDA_CD
                    ,PGTO_TNR_CD
                    ,PGTO_DT_LIMITE_PGTO_DEB
                    ,PGTO_DT_RECEP_REG_EM_AG
                    ,PGTO_IN_MEIO_RECEP_INF
                    ,PGTO_NR_CTRL_RECEP_INF
                    ,PGTO_IN_COND_SIT_REG
                    ,PGTO_IN_MOD_ARREC
                    ,PGTO_DT_APROPRIACAO
                    ,PGTO_DT_PERIODO_APROPRIA
                    ,PGTO_QT_DOC_AGREGADO
                    ,PGTO_TIIC_CD
                    ,PGTO_DOVR_NR_SQ
                    ,PGTO_TSGI_CD
                    ,PGTO_TQRP_CD
                    ,PGTO_TBFR_CD
                    ,PGTO_TPSI_CD
                    ,PGTO_UA_DT_PGTO
                    ,PGTO_UA_CLASS_CD
                    ,PGTO_MUNIC_CLASS_CD
                    ,PGTO_B_CD_MUN_DOM_CONTRIB
                    ,PGTO_TIFP_CD
                    ,PGTO_B_IN_CLASS_PRINC
                    ,PGTO_B_IN_CLASS_MULTA
                    ,PGTO_B_IN_CLASS_JUROS
                    ,PGTO_IN_MEIO_RECEP_PG_RF
                    ,PGTO_NR_CTRL_RECEP_PG_RF
                    ,PGTO_DT_GER_REG_SIST_ORIG
                    ,PGTO_IN_ORIGEM_AR
                    ,PGTO_B_VL_DJE_SUNL
                    ,PGTO_B_VL_DJE_SDNL
                    ,PGTO_B_VL_DJE_SUTD
                    ,PGTO_B_VL_DJE_SDTD
                    ,PGTO_B_VL_DJE_VTDC
                    ,PGTO_NIT_NI
                    ,PGTO_PGTO_NR_ID_AR
                    ,PGTO_B_IP_TERMINAL
                    ,PGTO_IN_DARA
               --     ,PGTO_B_IN_ALOCADO
                FROM
                    PAGAMENTOS@DL_POC08_EXTRATOR -- @DB_LINK_EXTRATOR
                WHERE
                    ( PGTO_ESTB_PJ_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 1, 8))
                        AND PGTO_ESTB_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 9, 4)) AND TIPO = 'J' )
                    OR ( PGTO_PF_NR = TO_NUMBER(SUBSTR(CRF_CHAR, 1, 9)) AND TIPO = 'F' )
            ) LOOP -- LOOP na tabela PAGAMENTOS (PRODUCAO)
                PAGAMENTO.PGTO_NR                   := P.PGTO_NR;
                PAGAMENTO.PGTO_DT_ARRECADACAO       := P.PGTO_DT_ARRECADACAO;
                PAGAMENTO.PGTO_DT_REGISTRO          := P.PGTO_DT_REGISTRO;
                PAGAMENTO.PGTO_IN_TP                := P.PGTO_IN_TP;
                PAGAMENTO.PGTO_IN_CONTRIB_N_IDENT   := P.PGTO_IN_CONTRIB_N_IDENT;
                PAGAMENTO.PGTO_SITU_CD              := P.PGTO_SITU_CD;
                PAGAMENTO.PGTO_IN_UTIL_COBRANCA     := P.PGTO_IN_UTIL_COBRANCA;
                PAGAMENTO.PGTO_VL_TOTAL             := P.PGTO_VL_TOTAL;
                PAGAMENTO.PGTO_B_REC_CD1            := P.PGTO_B_REC_CD1;
                PAGAMENTO.PGTO_B_VL1                := P.PGTO_B_VL1;
                PAGAMENTO.PGTO_IN_IMPEDE_ALOC       := P.PGTO_IN_IMPEDE_ALOC;
                PAGAMENTO.PGTO_IN_ORIGEM            := P.PGTO_IN_ORIGEM;
                PAGAMENTO.PGTO_AGEN_AGTA_CD         := P.PGTO_AGEN_AGTA_CD;
                PAGAMENTO.PGTO_AGEN_CD              := P.PGTO_AGEN_CD;
                PAGAMENTO.PGTO_IN_DESMEMBRADO       := P.PGTO_IN_DESMEMBRADO;
                PAGAMENTO.PGTO_B_VL_RLOCAL          := P.PGTO_B_VL_RLOCAL;
                PAGAMENTO.PGTO_B_VL_GPORTE          := P.PGTO_B_VL_GPORTE;
                PAGAMENTO.PGTO_B_IN_INTERESSE       := P.PGTO_B_IN_INTERESSE;
                PAGAMENTO.PGTO_B_IN_PASSIVO         := P.PGTO_B_IN_PASSIVO;
                PAGAMENTO.PGTO_B_IN_ATUALIZACAO     := P.PGTO_B_IN_ATUALIZACAO;
                PAGAMENTO.PGTO_B_DT_ATUALIZACAO     := P.PGTO_B_DT_ATUALIZACAO;
                PAGAMENTO.PGTO_B_IN_HISTORICO       := P.PGTO_B_IN_HISTORICO;
                PAGAMENTO.PGTO_UA                   := P.PGTO_UA;
                PAGAMENTO.PGTO_ESTB_PJ_NR           := P.PGTO_ESTB_PJ_NR;
                PAGAMENTO.PGTO_ESTB_NR              := P.PGTO_ESTB_NR;
                PAGAMENTO.PGTO_OEPG_CD              := P.PGTO_OEPG_CD;
                PAGAMENTO.PGTO_DT_VENCIMENTO        := P.PGTO_DT_VENCIMENTO;
                PAGAMENTO.PGTO_NR_PERIODO_APUR      := P.PGTO_NR_PERIODO_APUR;
                PAGAMENTO.PGTO_NR_PROCESSO          := P.PGTO_NR_PROCESSO;
                PAGAMENTO.PGTO_MCAN_CD              := P.PGTO_MCAN_CD;
                PAGAMENTO.PGTO_PC_REC_BRUT          := P.PGTO_PC_REC_BRUT;
                PAGAMENTO.PGTO_VL_REC_BRUT_ACUM     := P.PGTO_VL_REC_BRUT_ACUM;
                PAGAMENTO.PGTO_NR_REFERENCIA        := P.PGTO_NR_REFERENCIA;
                PAGAMENTO.PGTO_PROC_NR              := P.PGTO_PROC_NR;
                PAGAMENTO.PGTO_NR_PARCELA           := P.PGTO_NR_PARCELA;
                PAGAMENTO.PGTO_RELE_NR_SQ           := P.PGTO_RELE_NR_SQ;
                PAGAMENTO.PGTO_PF_NR                := P.PGTO_PF_NR;
                PAGAMENTO.PGTO_B_NR_BDA_SQ          := P.PGTO_B_NR_BDA_SQ;
                PAGAMENTO.PGTO_RPAP_NR_BDA          := P.PGTO_RPAP_NR_BDA;
                PAGAMENTO.PGTO_B_DT_RECEPCAO        := P.PGTO_B_DT_RECEPCAO;
                PAGAMENTO.PGTO_DT_ENCERRA_PER_APU   := P.PGTO_DT_ENCERRA_PER_APU;
                PAGAMENTO.PGTO_ALSP_CD              := P.PGTO_ALSP_CD;
                PAGAMENTO.PGTO_MNDP_CD              := P.PGTO_MNDP_CD;
                PAGAMENTO.PGTO_B_VL2                := P.PGTO_B_VL2;
                PAGAMENTO.PGTO_B_VL3                := P.PGTO_B_VL3;
                PAGAMENTO.PGTO_B_VL4                := P.PGTO_B_VL4;
                PAGAMENTO.PGTO_B_VL5                := P.PGTO_B_VL5;
                PAGAMENTO.PGTO_B_REC_CD2            := P.PGTO_B_REC_CD2;
                PAGAMENTO.PGTO_B_REC_CD3            := P.PGTO_B_REC_CD3;
                PAGAMENTO.PGTO_B_REC_CD4            := P.PGTO_B_REC_CD4;
                PAGAMENTO.PGTO_B_REC_CD5            := P.PGTO_B_REC_CD5;
                PAGAMENTO.PGTO_B_REC_DT1            := P.PGTO_B_REC_DT1;
                PAGAMENTO.PGTO_B_REC_DT2            := P.PGTO_B_REC_DT2;
                PAGAMENTO.PGTO_B_REC_DT3            := P.PGTO_B_REC_DT3;
                PAGAMENTO.PGTO_B_REC_DT4            := P.PGTO_B_REC_DT4;
                PAGAMENTO.PGTO_B_REC_DT5            := P.PGTO_B_REC_DT5;
                PAGAMENTO.PGTO_B_OEPG_DT            := P.PGTO_B_OEPG_DT;
                PAGAMENTO.PGTO_B_SITU_DT            := P.PGTO_B_SITU_DT;
                PAGAMENTO.PGTO_B_AGEN_DT            := P.PGTO_B_AGEN_DT;
                PAGAMENTO.PGTO_B_MCAN_DT            := P.PGTO_B_MCAN_DT;
                PAGAMENTO.PGTO_B_MNDP_DT            := P.PGTO_B_MNDP_DT;
                PAGAMENTO.PGTO_B_ALSP_DT            := P.PGTO_B_ALSP_DT;
                PAGAMENTO.PGTO_B_UA_CD_ATUALIZA     := P.PGTO_B_UA_CD_ATUALIZA;
                PAGAMENTO.PGTO_B_NR_TERMINAL        := P.PGTO_B_NR_TERMINAL;
                PAGAMENTO.PGTO_B_PF_NR_ATUALIZA     := P.PGTO_B_PF_NR_ATUALIZA;
                PAGAMENTO.PGTO_B_VL_RESTITUICAO     := P.PGTO_B_VL_RESTITUICAO;
                PAGAMENTO.PGTO_B_VL_COMPENSACAO     := P.PGTO_B_VL_COMPENSACAO;
                PAGAMENTO.PGTO_B_VL_ALOCADO_RL      := P.PGTO_B_VL_ALOCADO_RL;
                PAGAMENTO.PGTO_NR_CONTRIB_NIDENT    := P.PGTO_NR_CONTRIB_NIDENT;
                PAGAMENTO.PGTO_IN_AUTORIZA_DEB_CC   := P.PGTO_IN_AUTORIZA_DEB_CC;
                PAGAMENTO.PGTO_IN_MEIO_COLETA       := P.PGTO_IN_MEIO_COLETA;
                PAGAMENTO.PGTO_NR_PROTOCOL_DEB_CC   := P.PGTO_NR_PROTOCOL_DEB_CC;
                PAGAMENTO.PGTO_NR_ID_DEPOSITO_CEF   := P.PGTO_NR_ID_DEPOSITO_CEF;
                PAGAMENTO.PGTO_B_VL_SALDO_PRINCIPAL := P.PGTO_B_VL_SALDO_PRINCIPAL;
                PAGAMENTO.PGTO_B_VL_SALDO_MULTA     := P.PGTO_B_VL_SALDO_MULTA;
                PAGAMENTO.PGTO_B_VL_SALDO_JUROS     := P.PGTO_B_VL_SALDO_JUROS;
                PAGAMENTO.PGTO_B_IN_REC_INVAL       := P.PGTO_B_IN_REC_INVAL;
                PAGAMENTO.PGTO_B_IN_DELECAO_LOGICA  := P.PGTO_B_IN_DELECAO_LOGICA;
                PAGAMENTO.PGTO_NR_DV_CONTRIB        := P.PGTO_NR_DV_CONTRIB;
                PAGAMENTO.PGTO_B_DT_REGISTRO_GPORTE := P.PGTO_B_DT_REGISTRO_GPORTE;
                PAGAMENTO.PGTO_NR_ID_AR             := P.PGTO_NR_ID_AR;
                PAGAMENTO.PGTO_IN_INCONSISTENCIA    := P.PGTO_IN_INCONSISTENCIA;
                PAGAMENTO.PGTO_NR_REMESSA           := P.PGTO_NR_REMESSA;
                PAGAMENTO.PGTO_SEAR_TMAR_CD         := P.PGTO_SEAR_TMAR_CD;
                PAGAMENTO.PGTO_SEAR_TPR_CD          := P.PGTO_SEAR_TPR_CD;
                PAGAMENTO.PGTO_SEAR_TSP_CD          := P.PGTO_SEAR_TSP_CD;
                PAGAMENTO.PGTO_SEAR_TAUD_CD         := P.PGTO_SEAR_TAUD_CD;
                PAGAMENTO.PGTO_SEAR_TDA_CD          := P.PGTO_SEAR_TDA_CD;
                PAGAMENTO.PGTO_TNR_CD               := P.PGTO_TNR_CD;
                PAGAMENTO.PGTO_DT_LIMITE_PGTO_DEB   := P.PGTO_DT_LIMITE_PGTO_DEB;
                PAGAMENTO.PGTO_DT_RECEP_REG_EM_AG   := P.PGTO_DT_RECEP_REG_EM_AG;
                PAGAMENTO.PGTO_IN_MEIO_RECEP_INF    := P.PGTO_IN_MEIO_RECEP_INF;
                PAGAMENTO.PGTO_NR_CTRL_RECEP_INF    := P.PGTO_NR_CTRL_RECEP_INF;
                PAGAMENTO.PGTO_IN_COND_SIT_REG      := P.PGTO_IN_COND_SIT_REG;
                PAGAMENTO.PGTO_IN_MOD_ARREC         := P.PGTO_IN_MOD_ARREC;
                PAGAMENTO.PGTO_DT_APROPRIACAO       := P.PGTO_DT_APROPRIACAO;
                PAGAMENTO.PGTO_DT_PERIODO_APROPRIA  := P.PGTO_DT_PERIODO_APROPRIA;
                PAGAMENTO.PGTO_QT_DOC_AGREGADO      := P.PGTO_QT_DOC_AGREGADO;
                PAGAMENTO.PGTO_TIIC_CD              := P.PGTO_TIIC_CD;
                PAGAMENTO.PGTO_DOVR_NR_SQ           := P.PGTO_DOVR_NR_SQ;
                PAGAMENTO.PGTO_TSGI_CD              := P.PGTO_TSGI_CD;
                PAGAMENTO.PGTO_TQRP_CD              := P.PGTO_TQRP_CD;
                PAGAMENTO.PGTO_TBFR_CD              := P.PGTO_TBFR_CD;
                PAGAMENTO.PGTO_TPSI_CD              := P.PGTO_TPSI_CD;
                PAGAMENTO.PGTO_UA_DT_PGTO           := P.PGTO_UA_DT_PGTO;
                PAGAMENTO.PGTO_UA_CLASS_CD          := P.PGTO_UA_CLASS_CD;
                PAGAMENTO.PGTO_MUNIC_CLASS_CD       := P.PGTO_MUNIC_CLASS_CD;
                PAGAMENTO.PGTO_B_CD_MUN_DOM_CONTRIB := P.PGTO_B_CD_MUN_DOM_CONTRIB;
                PAGAMENTO.PGTO_TIFP_CD              := P.PGTO_TIFP_CD;
                PAGAMENTO.PGTO_B_IN_CLASS_PRINC     := P.PGTO_B_IN_CLASS_PRINC;
                PAGAMENTO.PGTO_B_IN_CLASS_MULTA     := P.PGTO_B_IN_CLASS_MULTA;
                PAGAMENTO.PGTO_B_IN_CLASS_JUROS     := P.PGTO_B_IN_CLASS_JUROS;
                PAGAMENTO.PGTO_IN_MEIO_RECEP_PG_RF  := P.PGTO_IN_MEIO_RECEP_PG_RF;
                PAGAMENTO.PGTO_NR_CTRL_RECEP_PG_RF  := P.PGTO_NR_CTRL_RECEP_PG_RF;
                PAGAMENTO.PGTO_DT_GER_REG_SIST_ORIG := P.PGTO_DT_GER_REG_SIST_ORIG;
                PAGAMENTO.PGTO_IN_ORIGEM_AR         := P.PGTO_IN_ORIGEM_AR;
                PAGAMENTO.PGTO_B_VL_DJE_SUNL        := P.PGTO_B_VL_DJE_SUNL;
                PAGAMENTO.PGTO_B_VL_DJE_SDNL        := P.PGTO_B_VL_DJE_SDNL;
                PAGAMENTO.PGTO_B_VL_DJE_SUTD        := P.PGTO_B_VL_DJE_SUTD;
                PAGAMENTO.PGTO_B_VL_DJE_SDTD        := P.PGTO_B_VL_DJE_SDTD;
                PAGAMENTO.PGTO_B_VL_DJE_VTDC        := P.PGTO_B_VL_DJE_VTDC;
                PAGAMENTO.PGTO_NIT_NI               := P.PGTO_NIT_NI;
                PAGAMENTO.PGTO_PGTO_NR_ID_AR        := P.PGTO_PGTO_NR_ID_AR;
                PAGAMENTO.PGTO_B_IP_TERMINAL        := P.PGTO_B_IP_TERMINAL;
                PAGAMENTO.PGTO_IN_DARA              := P.PGTO_IN_DARA;
        --        PAGAMENTO.PGTO_B_IN_ALOCADO         := P.PGTO_B_IN_ALOCADO;

                BEGIN
                    PR_INSERE_PGTO(PAGAMENTO);

                    QTD_PGTO := QTD_PGTO + 1;

                    EXCEPTION
                    WHEN ERRO_DOC_EXISTE THEN
                    BEGIN
                        COD_ERR  := -20001;
                        MSG_ERR  := 'Documento ' || TO_CHAR(PAGAMENTO.PGTO_NR) || ' ja existe na base de dados.';
                        IND_PROC := 'N';

                        ROLLBACK;
                    END;

                    WHEN OTHERS THEN
                    BEGIN
                        COD_ERR  := SQLCODE;
                        MSG_ERR  := SUBSTR(SQLERRM, 1, 200);
                        IND_PROC := 'N';

                        DBMS_OUTPUT.PUT_LINE('Documento: ' || TO_CHAR(PAGAMENTO.PGTO_NR));
                        DBMS_OUTPUT.PUT_LINE(SUBSTR('ERRO: ' || MSG_ERR, 1, 200));

                        ROLLBACK;
                    END;
                END;
            END LOOP;
        ELSE
            COD_ERR := -20001;
            MSG_ERR := 'Existem modulos(' || QTD_MODULO || ') predecessores na inclusao que terminaram com valores diferentes de S, 0, X';
        END IF;

        IF (QTD_PGTO <= 0 AND COD_ERR = 0) THEN
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 00: Nao existem documentos do NI ' || CRF_CHAR || TIPO || ' para serem incluidos.');
            IND_PROC := '0';
        ELSIF (QTD_PGTO > 0 AND COD_ERR = 0) THEN
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 00: Foram incluidos ' || QTD_PGTO || ' documentos do NI ' || CRF_CHAR || TIPO || ' com sucesso.');
            IND_PROC := 'S';
        ELSE
            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 3 - ERRO: ' || SUBSTR(MSG_ERR, 1, 200));
            DBMS_OUTPUT.PUT_LINE('ERRO: Ocorreram erros durante a extracao dos documentos do NI ' || CRF_CHAR || TIPO);
            IND_PROC := 'N';
        END IF;

        -- Atualizando o campo de status.
        UPDATE
            CONTRIBUINTE_HOM_TRE_APO
        SET
             COHT_IN_PGTO      = IND_PROC
            ,COHT_COD_ERRO_ORA = COD_ERR
        WHERE
            COHT_INHT_NR_SEQUENCIAL  = NI.COHT_INHT_NR_SEQUENCIAL
            AND COHT_IN_OPERACAO     = 'I'
            AND COHT_IN_CONTRIBUINTE = TIPO
            AND COHT_NR_NI           = CRF;

        COMMIT;

        DT_FIM := SYSDATE;

        DBMS_OUTPUT.PUT_LINE('Inicio PR_INCLUIR_PGTO: ' || TO_CHAR(DT_INI, 'dd/mm/yyyy hh24:mi:ss'));
        DBMS_OUTPUT.PUT_LINE('Fim    PR_INCLUIR_PGTO: ' || TO_CHAR(DT_FIM, 'dd/mm/yyyy hh24:mi:ss'));

        EXCEPTION
        WHEN OTHERS THEN
        BEGIN
            COD_ERR := SQLCODE;
            MSG_ERR := SUBSTR(SQLERRM, 1, 200);

            DBMS_OUTPUT.PUT_LINE('SIEF STATUS 3 - ERRO: ' || SUBSTR(SQLERRM, 1, 200));
            DBMS_OUTPUT.PUT_LINE('Contribuinte: ' || CRF || TIPO);

            IND_PROC := 'N';

            UPDATE
                CONTRIBUINTE_HOM_TRE_APO
            SET
                 COHT_IN_PGTO      = IND_PROC
                ,COHT_COD_ERRO_ORA = COD_ERR
            WHERE
                COHT_INHT_NR_SEQUENCIAL  = NI.COHT_INHT_NR_SEQUENCIAL
                AND COHT_IN_OPERACAO     = 'I'
                AND COHT_IN_CONTRIBUINTE = TIPO
                AND COHT_NR_NI           = CRF;

            COMMIT;
        END;
    END PR_INCLUIR_PGTO;
END PK_EXTRATOR_PGTO;

