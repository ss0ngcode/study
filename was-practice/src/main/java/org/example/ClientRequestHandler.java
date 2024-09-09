package org.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.example.calculator.Calculator;
import org.example.calculator.calculate.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRequestHandler implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);

	private final Socket clientSocket;

	public ClientRequestHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		/**
		 * Step2 - 사용자의 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 하낟.
		 */
		logger.info("[clientRequestHandler] new client {} started.", Thread.currentThread().getName());
		try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
			DataOutputStream dos = new DataOutputStream(out);

			HttpRequest httpRequest = new HttpRequest(br);

			if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
				QueryStrings queryStrings = httpRequest.getQueryStrings();

				int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
				String operator = queryStrings.getValue("operator");
				int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

				int result = Calculator.calculate(new PositiveNumber(operand1), operator,
					new PositiveNumber(operand2));
				byte[] body = String.valueOf(result).getBytes();

				HttpResponse response = new HttpResponse(dos);
				response.response200Header("application/json", body.length);
				response.responseBody(body);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
