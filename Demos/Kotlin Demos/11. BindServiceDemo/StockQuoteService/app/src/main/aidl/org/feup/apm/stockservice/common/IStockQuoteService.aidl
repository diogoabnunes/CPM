package org.feup.apm.stockservice.common;

import org.feup.apm.stockservice.common.IStockQuoteServiceResponse;
import org.feup.apm.stockservice.common.Person;

oneway interface IStockQuoteService {
  void getQuoteLong(in String ticker, in Person requester, in IStockQuoteServiceResponse response);
}