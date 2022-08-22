package com.example.demo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
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
public class Profit extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5061144f806100206000396000f3fe6080604052600436106100e85760003560e01c80636773f3821161008a5780639ea9c8fd116100595780639ea9c8fd14610426578063bea8df341461049e578063d8cb4aa314610503578063f8b2cb4f14610547576100e8565b80636773f3821461032b57806376fdcb701461039a5780638a4068dd146103f15780638eb19da7146103fb576100e8565b806311477c89116100c657806311477c89146101ba5780634b420d23146102325780635a532fc81461028357806366b220b9146102da576100e8565b8063048e6ee9146100ea5780630f4ae692146101155780630ff61fd514610163575b005b3480156100f657600080fd5b506100ff6105ac565b6040518082815260200191505060405180910390f35b6101616004803603604081101561012b57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506105cb565b005b34801561016f57600080fd5b506101786107c6565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610230600480360360808110156101d057600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506107ce565b005b34801561023e57600080fd5b506102816004803603602081101561025557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610cce565b005b34801561028f57600080fd5b50610298610d11565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102e657600080fd5b50610329600480360360208110156102fd57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d3b565b005b34801561033757600080fd5b506103846004803603604081101561034e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610d7f565b6040518082815260200191505060405180910390f35b3480156103a657600080fd5b506103af610dda565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6103f9610e03565b005b34801561040757600080fd5b50610410610e4c565b6040518082815260200191505060405180910390f35b61049c6004803603608081101561043c57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610e54565b005b3480156104aa57600080fd5b50610501600480360360608110156104c157600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291908035906020019092919050505061134e565b005b6105456004803603602081101561051957600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506113a8565b005b34801561055357600080fd5b506105966004803603602081101561056a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506113f9565b6040518082815260200191505060405180910390f35b60003373ffffffffffffffffffffffffffffffffffffffff1631905090565b60008273ffffffffffffffffffffffffffffffffffffffff1663d02407d4836040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b15801561061e57600080fd5b505afa158015610632573d6000803e3d6000fd5b505050506040513d602081101561064857600080fd5b8101908080519060200190929190505050905060008090505b818110156107c05760008473ffffffffffffffffffffffffffffffffffffffff166317ad810985846040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b1580156106c457600080fd5b505afa1580156106d8573d6000803e3d6000fd5b505050506040513d60208110156106ee57600080fd5b8101908080519060200190929190505050905060008573ffffffffffffffffffffffffffffffffffffffff16632ade61c286856040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b15801561075c57600080fd5b505afa158015610770573d6000803e3d6000fd5b505050506040513d602081101561078657600080fd5b810190808051906020019092919050505090506107a5828288886107ce565b6107b182828888610e54565b50508080600101915050610661565b50505050565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f09d47ff86866040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060206040518083038186803b15801561087657600080fd5b505afa15801561088a573d6000803e3d6000fd5b505050506040513d60208110156108a057600080fd5b8101908080519060200190929190505050905060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e43dd8b6836040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b15801561092757600080fd5b505afa15801561093b573d6000803e3d6000fd5b505050506040513d602081101561095157600080fd5b8101908080519060200190929190505050905060008090505b81811015610ae05760008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ab761f1785846040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b1580156109ee57600080fd5b505afa158015610a02573d6000803e3d6000fd5b505050506040513d6020811015610a1857600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff166108fc600a9081150290604051600060405180830381858888f19350505050158015610a72573d6000803e3d6000fd5b50600a600260008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008781526020019081526020016000206000828254039250508190555050808060010191505061096a565b5060008090505b8673ffffffffffffffffffffffffffffffffffffffff16631b3322c3876040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610b3857600080fd5b505afa158015610b4c573d6000803e3d6000fd5b505050506040513d6020811015610b6257600080fd5b8101908080519060200190929190505050811015610cc55760008773ffffffffffffffffffffffffffffffffffffffff1663c3e99c1388846040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b158015610bd557600080fd5b505afa158015610be9573d6000803e3d6000fd5b505050506040513d6020811015610bff57600080fd5b8101908080519060200190929190505050905060008873ffffffffffffffffffffffffffffffffffffffff16632e5c797389856040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b158015610c6d57600080fd5b505afa158015610c81573d6000803e3d6000fd5b505050506040513d6020811015610c9757600080fd5b81019080805190602001909291905050509050610cb6828289896107ce565b50508080600101915050610ae7565b50505050505050565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600083815260200190815260200160002054905092915050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b3073ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610e49573d6000803e3d6000fd5b50565b600047905090565b60008473ffffffffffffffffffffffffffffffffffffffff16635429b780856040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610ea757600080fd5b505afa158015610ebb573d6000803e3d6000fd5b505050506040513d6020811015610ed157600080fd5b8101908080519060200190929190505050905060008090505b818110156113465760008673ffffffffffffffffffffffffffffffffffffffff1663ba8b5e6087846040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b158015610f4d57600080fd5b505afa158015610f61573d6000803e3d6000fd5b505050506040513d6020811015610f7757600080fd5b8101908080519060200190929190505050905060008773ffffffffffffffffffffffffffffffffffffffff166380db10f588856040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b158015610fe557600080fd5b505afa158015610ff9573d6000803e3d6000fd5b505050506040513d602081101561100f57600080fd5b8101908080519060200190929190505050905060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f09d47ff84846040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060206040518083038186803b1580156110ca57600080fd5b505afa1580156110de573d6000803e3d6000fd5b505050506040513d60208110156110f457600080fd5b8101908080519060200190929190505050905060008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e43dd8b6836040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b15801561117b57600080fd5b505afa15801561118f573d6000803e3d6000fd5b505050506040513d60208110156111a557600080fd5b8101908080519060200190929190505050905060008090505b818110156113345760008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ab761f1785846040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b15801561124257600080fd5b505afa158015611256573d6000803e3d6000fd5b505050506040513d602081101561126c57600080fd5b810190808051906020019092919050505090508073ffffffffffffffffffffffffffffffffffffffff166108fc600a9081150290604051600060405180830381858888f193505050501580156112c6573d6000803e3d6000fd5b50600a600260008c73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008b8152602001908152602001600020600082825403925050819055505080806001019150506111be565b50505050508080600101915050610eea565b505050505050565b80600260008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600084815260200190815260200160002081905550505050565b8073ffffffffffffffffffffffffffffffffffffffff166108fc6113ca610e4c565b9081150290604051600060405180830381858888f193505050501580156113f5573d6000803e3d6000fd5b5050565b60008173ffffffffffffffffffffffffffffffffffffffff1631905091905056fea265627a7a723158206d68e57c9fb8889ed33489a54be61fafa77e38be6fc381923e9ea3784c6d762564736f6c63430005110032";

    public static final String FUNC_bonus = "bonus";

    public static final String FUNC_GETADDRESSMSGSENDER = "getAddressMsgSender";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETBALANCEMSGSENDER = "getBalanceMsgSender";

    public static final String FUNC_GETBALANCETHISCONTRACT = "getBalanceThisContract";

    public static final String FUNC_GETCOMMITTEEADDR = "getCommitteeAddr";

    public static final String FUNC_GETINCOME = "getIncome";

    public static final String FUNC_GETWORKADDR = "getWorkAddr";

    public static final String FUNC_SETCOMMITTEEADDR = "setCommitteeAddr";

    public static final String FUNC_SETINCOME = "setIncome";

    public static final String FUNC_SETWORKADDR = "setWorkAddr";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRAVERSAL = "traversal";

    public static final String FUNC_TRAVERSALABSTRACT = "traversalAbstract";

    @Deprecated
    protected Profit(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Profit(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Profit(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Profit(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> bonus(String _projectContract, BigInteger _projectTokenId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_bonus, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> bonus(String _addr, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_bonus, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> getAddressMsgSender() {
        final Function function = new Function(FUNC_GETADDRESSMSGSENDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getBalance(String _account) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getBalanceMsgSender() {
        final Function function = new Function(FUNC_GETBALANCEMSGSENDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getBalanceThisContract() {
        final Function function = new Function(FUNC_GETBALANCETHISCONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getCommitteeAddr() {
        final Function function = new Function(FUNC_GETCOMMITTEEADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getIncome(String _projectContract, BigInteger _projectTokenId) {
        final Function function = new Function(FUNC_GETINCOME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getWorkAddr() {
        final Function function = new Function(FUNC_GETWORKADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setCommitteeAddr(String _addr) {
        final Function function = new Function(
                FUNC_SETCOMMITTEEADDR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIncome(String _projectContract, BigInteger _projectTokenId, BigInteger _income) {
        final Function function = new Function(
                FUNC_SETINCOME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_income)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWorkAddr(String _addr) {
        final Function function = new Function(
                FUNC_SETWORKADDR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _addr)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> traversal(String _workContract, BigInteger _workId, String _projectContract, BigInteger _projectTokenId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TRAVERSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> traversalAbstract(String _workContract, BigInteger _workId, String _projectContract, BigInteger _projectTokenId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TRAVERSALABSTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static Profit load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Profit(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Profit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Profit(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Profit load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Profit(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Profit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Profit(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Profit> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Profit.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Profit> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Profit.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Profit> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Profit.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Profit> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Profit.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
