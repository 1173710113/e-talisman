package com.example.demo.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class Commodity extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506119c9806100206000396000f3fe6080604052600436106101355760003560e01c806367ccdf38116100ab578063ae77c2371161006f578063ae77c2371461071c578063b7245a051461076a578063c89c1866146107b1578063d7637b6f1461082c578063e7572230146108b1578063f7d975771461090057610135565b806367ccdf381461051d5780637494ad81146105985780638b4eb4571461060b5780638f022dc714610666578063a9059cbb146106c157610135565b8063356fb98a116100fd578063356fb98a1461031b57806340ace96d1461037657806344c9af28146103c5578063452788de146104185780634bf7d656146104675780634eeba5be146104a257610135565b8063010a38f51461013a57806315f4150114610165578063287e96c1146101e057806332a1f6291461023b57806334a920eb146102a0575b600080fd5b34801561014657600080fd5b5061014f610945565b6040518082815260200191505060405180910390f35b34801561017157600080fd5b506101de6004803603606081101561018857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061095a565b005b3480156101ec57600080fd5b506102396004803603604081101561020357600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610eb6565b005b34801561024757600080fd5b5061029e6004803603606081101561025e57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610f0c565b005b3480156102ac57600080fd5b506102d9600480360360208110156102c357600080fd5b8101908080359060200190929190505050610f66565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561032757600080fd5b506103746004803603604081101561033e57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610fa3565b005b34801561038257600080fd5b506103af6004803603602081101561039957600080fd5b8101908080359060200190929190505050610ff9565b6040518082815260200191505060405180910390f35b3480156103d157600080fd5b506103fe600480360360208110156103e857600080fd5b810190808035906020019092919050505061105b565b604051808215151515815260200191505060405180910390f35b34801561042457600080fd5b506104516004803603602081101561043b57600080fd5b8101908080359060200190929190505050611085565b6040518082815260200191505060405180910390f35b34801561047357600080fd5b506104a06004803603602081101561048a57600080fd5b81019080803590602001909291905050506110a2565b005b3480156104ae57600080fd5b506104db600480360360208110156104c557600080fd5b81019080803590602001909291905050506110c8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561052957600080fd5b506105566004803603602081101561054057600080fd5b8101908080359060200190929190505050611116565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156105a457600080fd5b506105f1600480360360408110156105bb57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611153565b604051808215151515815260200191505060405180910390f35b34801561061757600080fd5b506106646004803603604081101561062e57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506111d5565b005b34801561067257600080fd5b506106bf6004803603604081101561068957600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061124d565b005b3480156106cd57600080fd5b5061071a600480360360408110156106e457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506112a3565b005b6107686004803603604081101561073257600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611354565b005b34801561077657600080fd5b506107af6004803603604081101561078d57600080fd5b81019080803590602001909291908035151590602001909291905050506115f9565b005b3480156107bd57600080fd5b506107ea600480360360208110156107d457600080fd5b8101908080359060200190929190505050611628565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561083857600080fd5b5061086f6004803603604081101561084f57600080fd5b810190808035906020019092919080359060200190929190505050611665565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156108bd57600080fd5b506108ea600480360360208110156108d457600080fd5b81019080803590602001909291905050506116c8565b6040518082815260200191505060405180910390f35b34801561090c57600080fd5b506109436004803603604081101561092357600080fd5b8101908080359060200190929190803590602001909291905050506116e5565b005b60008060008154600101919050819055905090565b8273ffffffffffffffffffffffffffffffffffffffff166334a920eb836040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b1580156109ab57600080fd5b505afa1580156109bf573d6000803e3d6000fd5b505050506040513d60208110156109d557600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610a1d57600080fd5b60006001905060008314610d105760008473ffffffffffffffffffffffffffffffffffffffff1663d02407d4856040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015610a7e57600080fd5b505afa158015610a92573d6000803e3d6000fd5b505050506040513d6020811015610aa857600080fd5b8101908080519060200190929190505050905060008090505b81811015610d0d5760008673ffffffffffffffffffffffffffffffffffffffff166317ad810987846040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b158015610b2457600080fd5b505afa158015610b38573d6000803e3d6000fd5b505050506040513d6020811015610b4e57600080fd5b8101908080519060200190929190505050905060008773ffffffffffffffffffffffffffffffffffffffff16632ade61c288856040518363ffffffff1660e01b8152600401808381526020018281526020019250505060206040518083038186803b158015610bbc57600080fd5b505afa158015610bd0573d6000803e3d6000fd5b505050506040513d6020811015610be657600080fd5b81019080805190602001909291905050509050600015158673ffffffffffffffffffffffffffffffffffffffff1663df0863428484336040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001935050505060206040518083038186803b158015610cb657600080fd5b505afa158015610cca573d6000803e3d6000fd5b505050506040513d6020811015610ce057600080fd5b810190808051906020019092919050505015151415610cfe57600094505b50508080600101915050610ac1565b50505b80610d1a57600080fd5b6000610d24610945565b905060003082604051610d3690611701565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f080158015610d8f573d6000803e3d6000fd5b509050610d9c8282610eb6565b610da7828787610f0c565b610db1828761124d565b610dbb82336111d5565b610dc58233610fa3565b610dd08260016115f9565b8573ffffffffffffffffffffffffffffffffffffffff1663d653174b8630856040518463ffffffff1660e01b8152600401808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050600060405180830381600087803b158015610e5f57600080fd5b505af1158015610e73573d6000803e3d6000fd5b505050507f17b495b7ec1ce0838ef8f26f6c4823c3eabd70e62f0d855b6595f3dd409a47c5826040518082815260200191505060405180910390a1505050505050565b806001600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b806002600085815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550505050565b60006006600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b806006600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600060026000838152602001908152602001600020600061101984611628565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60006008600083815260200190815260200160002060009054906101000a900460ff169050919050565b600060056000838152602001908152602001600020549050919050565b600560008281526020019081526020016000206000815480929190600101919050555050565b600060046000838152602001908152602001600020600080815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6000806000905060008061116686611085565b905060008092505b818310156111c8576111808784611665565b90508073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff1614156111bb57600193505b828060010193505061116e565b8394505050505092915050565b806004600084815260200190815260200160002060006111f485611085565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611249826110a2565b5050565b806003600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6112ac81610f66565b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146112e357600080fd5b60016112ee82611085565b141561132457600015156113028284611153565b151514156113155761131481836111d5565b5b61131f8183610fa3565b611350565b600015156113328284611153565b151514156113455761134481836111d5565b5b61134f8183610fa3565b5b5050565b600115156113618361105b565b15151461136d57600080fd5b611376826116c8565b34101561138257600080fd5b600161138d83611085565b1415611574578073ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f193505050501580156113d9573d6000803e3d6000fd5b50600015156113e88333611153565b151514156113fb576113fa82336111d5565b5b600061140683610ff9565b9050600061141384611628565b90508273ffffffffffffffffffffffffffffffffffffffff1663bea8df348284346040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018281526020019350505050600060405180830381600087803b1580156114a457600080fd5b505af11580156114b8573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff16638031338c8330876040518463ffffffff1660e01b8152600401808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050600060405180830381600087803b15801561154b57600080fd5b505af115801561155f573d6000803e3d6000fd5b5050505061156d8433610fa3565b50506115f5565b600061157f83610f66565b90508073ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f193505050501580156115c7573d6000803e3d6000fd5b50600015156115d68433611153565b151514156115e9576115e883336111d5565b5b6115f38333610fa3565b505b5050565b806008600084815260200190815260200160002060006101000a81548160ff0219169083151502179055505050565b60006003600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600061167083611085565b821061167b57600080fd5b60046000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b600060076000838152602001908152602001600020549050919050565b8060076000848152602001908152602001600020819055505050565b6102868061170f8339019056fe608060405234801561001057600080fd5b506040516102863803806102868339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060018190555050506101e0806100a66000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063010a38f514610051578063174ae4431461006f5780634c901b8c146100b3578063c929ccf3146100fd575b600080fd5b61005961012b565b6040518082815260200191505060405180910390f35b6100b16004803603602081101561008557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610135565b005b6100bb610178565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6101296004803603602081101561011357600080fd5b81019080803590602001909291905050506101a1565b005b6000600154905090565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b806001819055505056fea265627a7a7231582004341fecfde90f790965f368a33dc3185169fea57e47120836de9d7b6e863a8164736f6c63430005110032a265627a7a723158202a564090dde72e0af37e68f7dc094b08f610558e94b36881ab7beddf01ec7cad64736f6c63430005110032";

    public static final String FUNC_ADDONEOWNERNUM = "addOneOwnerNum";

    public static final String FUNC_GETORIGINALOWNER = "getOriginalOwner";

    public static final String FUNC_GETOWNERBYCOMMODITYTOKENIDANDINDEX = "getOwnerByCommodityTokenIdAndIndex";

    public static final String FUNC_GETOWNERNUM = "getOwnerNum";

    public static final String FUNC_GETPRESENTOWNER = "getPresentOwner";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_GETPROJECTCONTRACTBYCOMMODITYTOKENID = "getProjectContractByCommodityTokenId";

    public static final String FUNC_GETPROJECTIDBYCOMMODITYTOKENID = "getProjectIdByCommodityTokenId";

    public static final String FUNC_GETSTATE = "getState";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_JUDGE = "judge";

    public static final String FUNC_PURCHASE = "purchase";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_SETOWNERSHIPBYCOMMODITYTOKENID = "setOwnerShipByCommodityTokenId";

    public static final String FUNC_SETPRESENTOWNER = "setPresentOwner";

    public static final String FUNC_SETPRICE = "setPrice";

    public static final String FUNC_SETPROJECTCONTRACTBYCOMMODITYTOKENID = "setProjectContractByCommodityTokenId";

    public static final String FUNC_SETPROJECTIDBYCOMMODITYTOKENID = "setProjectIdByCommodityTokenId";

    public static final String FUNC_SETSTATE = "setState";

    public static final String FUNC_SETTOKENADDRESS = "setTokenAddress";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event AFTERRELEASE_EVENT = new Event("afterRelease", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Commodity(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Commodity(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Commodity(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Commodity(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AfterReleaseEventResponse> getAfterReleaseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AFTERRELEASE_EVENT, transactionReceipt);
        ArrayList<AfterReleaseEventResponse> responses = new ArrayList<AfterReleaseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AfterReleaseEventResponse typedResponse = new AfterReleaseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._commodityId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AfterReleaseEventResponse> afterReleaseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AfterReleaseEventResponse>() {
            @Override
            public AfterReleaseEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AFTERRELEASE_EVENT, log);
                AfterReleaseEventResponse typedResponse = new AfterReleaseEventResponse();
                typedResponse.log = log;
                typedResponse._commodityId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AfterReleaseEventResponse> afterReleaseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AFTERRELEASE_EVENT));
        return afterReleaseEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addOneOwnerNum(BigInteger _commodityTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDONEOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOriginalOwner(BigInteger _commodityTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORIGINALOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOwnerByCommodityTokenIdAndIndex(BigInteger _commodityTokenID, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERBYCOMMODITYTOKENIDANDINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getOwnerNum(BigInteger _commodityTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPresentOwner(BigInteger _commodityTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getPrice(BigInteger _commodityTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getProjectContractByCommodityTokenId(BigInteger _commodityTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROJECTCONTRACTBYCOMMODITYTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getProjectIdByCommodityTokenId(BigInteger _commodityTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROJECTIDBYCOMMODITYTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> getState(BigInteger _commodityTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSTATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> getTokenAddress(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOKENADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getTokenId() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETTOKENID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> judge(BigInteger _commodityTokenID, String _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_JUDGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> purchase(BigInteger _commodityTokenId, String _profitContract, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PURCHASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenId), 
                new org.web3j.abi.datatypes.Address(160, _profitContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String _projectContract, BigInteger _projectTokenId, String _commodifyRight) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _commodifyRight)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwnerShipByCommodityTokenId(BigInteger _commodityTokenID, String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETOWNERSHIPBYCOMMODITYTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPresentOwner(BigInteger _commodityTokenID, String _presentOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID), 
                new org.web3j.abi.datatypes.Address(160, _presentOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPrice(BigInteger _commodityTokenId, BigInteger _price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setProjectContractByCommodityTokenId(BigInteger _commodityTokenID, String _projectContract) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPROJECTCONTRACTBYCOMMODITYTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID), 
                new org.web3j.abi.datatypes.Address(160, _projectContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setProjectIdByCommodityTokenId(BigInteger _commodityTokenID, String _projectContract, BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPROJECTIDBYCOMMODITYTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenID), 
                new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setState(BigInteger _commodityTokenId, Boolean _state) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSTATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenId), 
                new org.web3j.abi.datatypes.Bool(_state)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenAddress(BigInteger _ipTokenId, String _tokenAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETTOKENADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String _to, BigInteger _commodityTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_commodityTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Commodity load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Commodity(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Commodity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Commodity(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Commodity load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Commodity(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Commodity load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Commodity(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Commodity> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Commodity.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Commodity> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Commodity.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Commodity> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Commodity.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Commodity> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Commodity.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AfterReleaseEventResponse extends BaseEventResponse {
        public BigInteger _commodityId;
    }
}
