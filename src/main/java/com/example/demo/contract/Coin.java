package com.example.demo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class Coin extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506040516102863803806102868339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060018190555050506101e0806100a66000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063010a38f514610051578063174ae4431461006f5780634c901b8c146100b3578063c929ccf3146100fd575b600080fd5b61005961012b565b6040518082815260200191505060405180910390f35b6100b16004803603602081101561008557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610135565b005b6100bb610178565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6101296004803603602081101561011357600080fd5b81019080803590602001909291905050506101a1565b005b6000600154905090565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b806001819055505056fea265627a7a72315820b0be30a30eef109e872a9a4a7a3dfb06e8dfc06dd29de5ada978e4db1414744a64736f6c63430005110032";

    public static final String FUNC_GETFROMCONTRACT = "getFromContract";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_SETFROMCONTRACT = "setFromContract";

    public static final String FUNC_SETTOKENID = "setTokenId";

    @Deprecated
    protected Coin(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Coin(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Coin(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Coin(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getFromContract() {
        final Function function = new Function(FUNC_GETFROMCONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getTokenId() {
        final Function function = new Function(FUNC_GETTOKENID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setFromContract(String _fromContract) {
        final Function function = new Function(
                FUNC_SETFROMCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _fromContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenId(BigInteger _tokenId) {
        final Function function = new Function(
                FUNC_SETTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Coin load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Coin(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Coin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Coin(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Coin load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Coin(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Coin load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Coin(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Coin> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _fromContract, BigInteger _tokenId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _fromContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)));
        return deployRemoteCall(Coin.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Coin> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _fromContract, BigInteger _tokenId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _fromContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)));
        return deployRemoteCall(Coin.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Coin> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _fromContract, BigInteger _tokenId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _fromContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)));
        return deployRemoteCall(Coin.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Coin> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _fromContract, BigInteger _tokenId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _fromContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_tokenId)));
        return deployRemoteCall(Coin.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
