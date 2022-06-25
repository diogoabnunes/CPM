package org.feup.apm.stockservice.common;

oneway interface IStockQuoteServiceResponse {
    void onQuoteResult(in String result);
}
