	@Override
	public List<ReceitaValorTO> calculaListaReceitaValor(DocumentoArrecadacaoRetificacaoBase1TO documentoPARADTO,
			BigInteger codigoReceita) {
		Pagamento documentoAcoplado = pagamentosDAO.findById(new BigInteger(documentoPARADTO.getNumeroRegistro()));

		if (documentoAcoplado == null) {
			throw new RdocSuiteException(RdocSuiteCoreMsg.DOCUMENTO_ARRECADACAO_NULO.getValue());
		}

		Map<String, Receita> mapReceita = retificacaoListaReceitasBC.calculaListaReceita(documentoAcoplado, codigoReceita);

		List<ReceitaValorTO> listaReceitaValorTOCalculada = new ArrayList<>();

		Receita receita01Calculada = mapReceita.get(MapReceitaConstante.RECEITA_01.getDescricao());

		if (receita01Calculada != null) {
			ReceitaValorTO receitaValorTO = new ReceitaValorTO();

			receitaValorTO.setCodigoReceita(StringUtil.leftPad(receita01Calculada.getRecCd(), 4, '0'));
			receitaValorTO.setTipoReceita(MapReceitaConstante.RECEITA_01.getDescricao());
			receitaValorTO.setValorReceita(BigDecimal.ZERO);
			receitaValorTO.setExigeReferencia(!BigInteger.ZERO.equals(receita01Calculada.getRecRdrfCd()));

			listaReceitaValorTOCalculada.add(receitaValorTO);
		}

		Receita receita02Calculada = mapReceita.get(MapReceitaConstante.RECEITA_02.getDescricao());

		if (receita02Calculada != null) {
			ReceitaValorTO receitaValorTO = new ReceitaValorTO();

			receitaValorTO.setCodigoReceita(StringUtil.leftPad(receita02Calculada.getRecCd(), 4, '0'));
			receitaValorTO.setTipoReceita(MapReceitaConstante.RECEITA_02.getDescricao());
			receitaValorTO.setValorReceita(BigDecimal.ZERO);
			receitaValorTO.setExigeReferencia(!BigInteger.ZERO.equals(receita02Calculada.getRecRdrfCd()));

			listaReceitaValorTOCalculada.add(receitaValorTO);
		}

		Receita receita03Calculada = mapReceita.get(MapReceitaConstante.RECEITA_03.getDescricao());

		if (receita03Calculada != null) {
			ReceitaValorTO receitaValorTO = new ReceitaValorTO();

			receitaValorTO.setCodigoReceita(StringUtil.leftPad(receita03Calculada.getRecCd(), 4, '0'));
			receitaValorTO.setTipoReceita(MapReceitaConstante.RECEITA_03.getDescricao());
			receitaValorTO.setValorReceita(BigDecimal.ZERO);
			receitaValorTO.setExigeReferencia(!BigInteger.ZERO.equals(receita03Calculada.getRecRdrfCd()));

			listaReceitaValorTOCalculada.add(receitaValorTO);
		}

		Integer qtdReceita = documentoPARADTO.calculaQuantidadeReceita();

		if (listaReceitaValorTOCalculada.size() == 1) {
			// de x para 1
			listaReceitaValorTOCalculada.get(0).setValorReceita(documentoPARADTO.getValorTotal());
		} else if (qtdReceita == 1) {
			// de 1 para x
			Receita receita01DocOriginal = null;

			if (!StringUtil.isNullOrEmpty(documentoPARADTO.getCodigoReceita01())) {
				try {
					receita01DocOriginal = receitaDAO.get(new BigInteger(documentoPARADTO.getCodigoReceita01()));
				} catch (EntityNotFoundException e) {
					receita01DocOriginal = receita01Calculada;
				}
			}

			if (receita01DocOriginal != null && receita01Calculada != null
					&& ComparadorUtil.igual(receita01DocOriginal.getTipo(), receita01Calculada.getTipo())
					&& ComparadorUtil.igual(receita01DocOriginal.getRecInTpEncargo(),
							receita01Calculada.getRecInTpEncargo())) {
				ReceitaValorTO receitaValorTO = new ReceitaValorTO();

				receitaValorTO.setCodigoReceita(StringUtil.leftPad(receita01Calculada.getRecCd(), 4, '0'));
				receitaValorTO.setTipoReceita(MapReceitaConstante.RECEITA_01.getDescricao());

				listaReceitaValorTOCalculada.get(listaReceitaValorTOCalculada.indexOf(receitaValorTO))
						.setValorReceita(documentoPARADTO.getValorReceita01());
			} else if (receita01DocOriginal != null && receita02Calculada != null
					&& ComparadorUtil.igual(receita01DocOriginal.getTipo(), receita02Calculada.getTipo())
					&& ComparadorUtil.igual(receita01DocOriginal.getRecInTpEncargo(),
							receita02Calculada.getRecInTpEncargo())) {
				ReceitaValorTO receitaValorTO = new ReceitaValorTO();

				receitaValorTO.setCodigoReceita(StringUtil.leftPad(receita02Calculada.getRecCd(), 4, '0'));
				receitaValorTO.setTipoReceita(MapReceitaConstante.RECEITA_02.getDescricao());

				listaReceitaValorTOCalculada.get(listaReceitaValorTOCalculada.indexOf(receitaValorTO))
						.setValorReceita(documentoPARADTO.getValorReceita01());
			} else if (receita03Calculada != null) {
				ReceitaValorTO receitaValorTO = new ReceitaValorTO();

				receitaValorTO.setCodigoReceita(StringUtil.leftPad(receita03Calculada.getRecCd(), 4, '0'));
				receitaValorTO.setTipoReceita(MapReceitaConstante.RECEITA_03.getDescricao());

				listaReceitaValorTOCalculada.get(listaReceitaValorTOCalculada.indexOf(receitaValorTO))
						.setValorReceita(documentoPARADTO.getValorReceita01());
			}
		} else if (qtdReceita == listaReceitaValorTOCalculada.size()) {
			Integer indice = 0;

			if (documentoPARADTO.getCodigoReceita01() != null && !"".equals(documentoPARADTO.getCodigoReceita01())) {
				listaReceitaValorTOCalculada.get(indice).setValorReceita(documentoPARADTO.getValorReceita01());
				indice++;
			}

			if (documentoPARADTO.getCodigoReceita02() != null && !"".equals(documentoPARADTO.getCodigoReceita02())) {
				listaReceitaValorTOCalculada.get(indice).setValorReceita(documentoPARADTO.getValorReceita02());
				indice++;
			}

			if (documentoPARADTO.getCodigoReceita03() != null && !"".equals(documentoPARADTO.getCodigoReceita03())) {
				listaReceitaValorTOCalculada.get(indice).setValorReceita(documentoPARADTO.getValorReceita03());
			}
		} else if (qtdReceita == 3 && listaReceitaValorTOCalculada.size() == 2) {
			// de 3 para 2
			listaReceitaValorTOCalculada.get(0).setValorReceita(documentoPARADTO.getValorReceita01());
			BigDecimal a = documentoPARADTO.getValorReceita02();
			BigDecimal b = documentoPARADTO.getValorReceita03();
			BigDecimal s = a.add(b);
			listaReceitaValorTOCalculada.get(1).setValorReceita(s);
		} else if (qtdReceita == 2 && listaReceitaValorTOCalculada.size() == 3) {
			// de 2 para 3
			listaReceitaValorTOCalculada.get(0).setValorReceita(documentoPARADTO.getValorReceita01());

			if ("S".equalsIgnoreCase(documentoAcoplado.getPgtoBInRecInval())) {
				if (documentoAcoplado.getPgtoBVl2() != null
						&& BigDecimal.ZERO.compareTo(documentoAcoplado.getPgtoBVl2()) < 0) {
					listaReceitaValorTOCalculada.get(1).setValorReceita(documentoPARADTO.getValorReceita02());
				} else if (documentoAcoplado.getPgtoBVl3() != null
						&& BigDecimal.ZERO.compareTo(documentoAcoplado.getPgtoBVl3()) < 0) {
					listaReceitaValorTOCalculada.get(2).setValorReceita(documentoPARADTO.getValorReceita02());
				}
			} else {
				// verifica se segunda receita eh de multa ou principal. Se for, coloca na
				// segunda posicao. Caso contrario coloca na terceira
				Receita receita02DocOriginal = null;

				if (!StringUtil.isNullOrEmpty(documentoPARADTO.getCodigoReceita02())) {
					try {
						receita02DocOriginal = receitaDAO.get(new BigInteger(documentoPARADTO.getCodigoReceita02()));
					} catch (EntityNotFoundException e) {
						receita02DocOriginal = receita02Calculada;
					}
				}

				if (receita02DocOriginal != null && (receita02DocOriginal.getRecInTpEncargo() == null
						|| "ML".equals(receita02DocOriginal.getRecInTpEncargo()))) {
					listaReceitaValorTOCalculada.get(1).setValorReceita(documentoPARADTO.getValorReceita02());
				} else {
					listaReceitaValorTOCalculada.get(2).setValorReceita(documentoPARADTO.getValorReceita02());
				}
			}
		}

		return listaReceitaValorTOCalculada;
	}
