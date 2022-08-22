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
import org.web3j.abi.datatypes.Utf8String;
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
public class Project extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50611f31806100206000396000f3fe608060405234801561001057600080fd5b50600436106102275760003560e01c80637494ad8111610130578063c3f15d00116100b8578063d87667711161007c578063d876677114610c86578063ec8433c514610cf2578063ed72439414610d40578063ef67ebce14610d82578063f3358ac414610db057610227565b8063c3f15d0014610a48578063c6722ce214610b0d578063cee6a9d514610b45578063d02407d414610bec578063d653174b14610c2e57610227565b806386d1a69f116100ff57806386d1a69f146108f4578063a67e845914610912578063ae99f24a14610940578063b69712b2146109b8578063b7760c8f146109fa57610227565b80637494ad81146107b05780637501349214610816578063752783621461086e5780638031338c1461089c57610227565b80632c24119b116101b35780634bf7d656116101825780634bf7d656146106005780634ce6cf741461062e5780634eeba5be1461068657806364ea3fc7146106f457806367ccdf381461074257610227565b80632c24119b146104d457806334a920eb14610502578063356fb98a14610570578063452788de146105be57610227565b806317ad8109116101fa57806317ad8109146103525780631815331e146103ca5780631da8b42f14610402578063287e96c11461043a5780632ade61c21461048857610227565b8063010a38f51461022c5780630683bd701461024a5780630884c19f1461028c578063116d59e414610304575b600080fd5b610234610df2565b6040518082815260200191505060405180910390f35b6102766004803603602081101561026057600080fd5b8101908080359060200190929190505050610e08565b6040518082815260200191505060405180910390f35b6102c2600480360360408110156102a257600080fd5b810190808035906020019092919080359060200190929190505050610e25565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6103506004803603604081101561031a57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e88565b005b6103886004803603604081101561036857600080fd5b810190808035906020019092919080359060200190929190505050610f00565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610400600480360360408110156103e057600080fd5b810190808035906020019092919080359060200190929190505050610f64565b005b6104386004803603604081101561041857600080fd5b810190808035906020019092919080359060200190929190505050610f89565b005b6104866004803603604081101561045057600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610fa5565b005b6104be6004803603604081101561049e57600080fd5b810190808035906020019092919080359060200190929190505050610ffb565b6040518082815260200191505060405180910390f35b610500600480360360208110156104ea57600080fd5b810190808035906020019092919050505061103e565b005b61052e6004803603602081101561051857600080fd5b8101908080359060200190929190505050611064565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6105bc6004803603604081101561058657600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506110a1565b005b6105ea600480360360208110156105d457600080fd5b81019080803590602001909291905050506110f7565b6040518082815260200191505060405180910390f35b61062c6004803603602081101561061657600080fd5b8101908080359060200190929190505050611114565b005b6106846004803603606081101561064457600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061113a565b005b6106b26004803603602081101561069c57600080fd5b81019080803590602001909291905050506111b1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6107406004803603604081101561070a57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506111ff565b005b61076e6004803603602081101561075857600080fd5b8101908080359060200190929190505050611277565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6107fc600480360360408110156107c657600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506112b4565b604051808215151515815260200191505060405180910390f35b61086c6004803603606081101561082c57600080fd5b810190808035906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611336565b005b61089a6004803603602081101561088457600080fd5b8101908080359060200190929190505050611491565b005b6108f2600480360360608110156108b257600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506114b7565b005b6108fc6115ba565b6040518082815260200191505060405180910390f35b61093e6004803603602081101561092857600080fd5b81019080803590602001909291905050506116f8565b005b6109766004803603604081101561095657600080fd5b81019080803590602001909291908035906020019092919050505061171e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6109e4600480360360208110156109ce57600080fd5b8101908080359060200190929190505050611781565b6040518082815260200191505060405180910390f35b610a4660048036036040811015610a1057600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061179e565b005b610b0b60048036036040811015610a5e57600080fd5b810190808035906020019092919080359060200190640100000000811115610a8557600080fd5b820183602082011115610a9757600080fd5b80359060200191846001830284011164010000000083111715610ab957600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f82011690508083019250505050505050919291929050505061180d565b005b610b4360048036036040811015610b2357600080fd5b810190808035906020019092919080359060200190929190505050611838565b005b610b7160048036036020811015610b5b57600080fd5b8101908080359060200190929190505050611927565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610bb1578082015181840152602081019050610b96565b50505050905090810190601f168015610bde5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b610c1860048036036020811015610c0257600080fd5b81019080803590602001909291905050506119db565b6040518082815260200191505060405180910390f35b610c8460048036036060811015610c4457600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506119f8565b005b610cdc60048036036060811015610c9c57600080fd5b810190808035906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611a74565b6040518082815260200191505060405180910390f35b610d3e60048036036040811015610d0857600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611af5565b005b610d6c60048036036020811015610d5657600080fd5b8101908080359060200190929190505050611b64565b6040518082815260200191505060405180910390f35b610dae60048036036020811015610d9857600080fd5b8101908080359060200190929190505050611b81565b005b610ddc60048036036020811015610dc657600080fd5b8101908080359060200190929190505050611ba7565b6040518082815260200191505060405180910390f35b6000600160008154600101919050819055905090565b6000600e6000838152602001908152602001600020549050919050565b6000610e30836110f7565b8210610e3b57600080fd5b60076000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b80600760008481526020019081526020016000206000610ea7856110f7565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610efc82611114565b5050565b6000610f0b836119db565b821115610f1757600080fd5b60036000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b80600f6000848152602001908152602001600020600082825401925050819055505050565b8060056000848152602001908152602001600020819055505050565b806002600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6000611006836119db565b821061101157600080fd5b60046000848152602001908152602001600020600083815260200190815260200160002054905092915050565b600a60008281526020019081526020016000206000815480929190600101919050555050565b6000600b600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600b600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600060066000838152602001908152602001600020549050919050565b600660008281526020019081526020016000206000815480929190600101919050555050565b80600c600085815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061119983836111ff565b6111a283611b81565b6111ac8382610f64565b505050565b600060076000838152602001908152602001600020600080815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600d6000848152602001908152602001600020600061121e85610e08565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061127382611b81565b5050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080600090506000806112c7866110f7565b905060008092505b81831015611329576112e18784610e25565b90508073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff16141561131c57600193505b82806001019350506112cf565b8394505050505092915050565b81600460008581526020019081526020016000206000611355866119db565b81526020019081526020016000208190555080600360008581526020019081526020016000206000611386866119db565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506113dd836000610f00565b73ffffffffffffffffffffffffffffffffffffffff16631bc69b848330866040518463ffffffff1660e01b8152600401808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050600060405180830381600087803b15801561146b57600080fd5b505af115801561147f573d6000803e3d6000fd5b5050505061148c836116f8565b505050565b600960008281526020019081526020016000206000815480929190600101919050555050565b60008090505b6114c684611ba7565b8110156115ab578160086000868152602001908152602001600020600083815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054141561159e57600060086000868152602001908152602001600020600083815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505b80806001019150506114bd565b6115b48461103e565b50505050565b6000806115c5610df2565b9050600030826040516115d790611bc4565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f080158015611630573d6000803e3d6000fd5b50905061163d8282610fa5565b6116478233610e88565b61165182336110a1565b7fbe32b067280c13748f30f66ec8b6f05a9a6fe5bb13d026fa6e0ca0a6b912cbbc823033604051808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001935050505060405180910390a1819250505090565b600560008281526020019081526020016000206000815480929190600101919050555050565b600061172983610e08565b821061173457600080fd5b600d6000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b6000600a6000838152602001908152602001600020549050919050565b6117a782611064565b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16146117de57600080fd5b600015156117ec83836112b4565b151514156117ff576117fe8282610e88565b5b61180982826110a1565b5050565b806000808481526020019081526020016000209080519060200190611833929190611bd1565b505050565b80600460008481526020019081526020016000206000611857856119db565b815260200190815260200160002081905550611874826000610f00565b73ffffffffffffffffffffffffffffffffffffffff16631bc69b848230856040518463ffffffff1660e01b8152600401808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050600060405180830381600087803b15801561190257600080fd5b505af1158015611916573d6000803e3d6000fd5b50505050611923826116f8565b5050565b60606000808381526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156119cf5780601f106119a4576101008083540402835291602001916119cf565b820191906000526020600020905b8154815290600101906020018083116119b257829003601f168201915b50505050509050919050565b600060056000838152602001908152602001600020549050919050565b80600860008581526020019081526020016000206000611a1786611ba7565b815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611a6f83611491565b505050565b6000611a7f84611ba7565b8310611a8a57600080fd5b60086000858152602001908152602001600020600084815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205490509392505050565b80600360008481526020019081526020016000206000611b14856119db565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6000600f6000838152602001908152602001600020549050919050565b600e60008281526020019081526020016000206000815480929190600101919050555050565b600060096000838152602001908152602001600020549050919050565b61028680611c7783390190565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611c1257805160ff1916838001178555611c40565b82800160010185558215611c40579182015b82811115611c3f578251825591602001919060010190611c24565b5b509050611c4d9190611c51565b5090565b611c7391905b80821115611c6f576000816000905550600101611c57565b5090565b9056fe608060405234801561001057600080fd5b506040516102863803806102868339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060018190555050506101e0806100a66000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063010a38f514610051578063174ae4431461006f5780634c901b8c146100b3578063c929ccf3146100fd575b600080fd5b61005961012b565b6040518082815260200191505060405180910390f35b6100b16004803603602081101561008557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610135565b005b6100bb610178565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6101296004803603602081101561011357600080fd5b81019080803590602001909291905050506101a1565b005b6000600154905090565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b806001819055505056fea265627a7a7231582004341fecfde90f790965f368a33dc3185169fea57e47120836de9d7b6e863a8164736f6c63430005110032a265627a7a7231582028fefd9ec1d51708fa874eeb2071c44d1f673e6e1fb08974ea02570b8c180b5364736f6c63430005110032";

    public static final String FUNC_ADDINVESTORNUM = "addInvestorNum";

    public static final String FUNC_ADDNUM = "addNum";

    public static final String FUNC_ADDONEOWNERNUM = "addOneOwnerNum";

    public static final String FUNC_ADDSOLDNUM = "addSoldNum";

    public static final String FUNC_ADDTOTALINVESTMONEY = "addTotalInvestMoney";

    public static final String FUNC_ADDWORK = "addWork";

    public static final String FUNC_ADDWORKNUM = "addWorkNum";

    public static final String FUNC_GETDEPICT = "getDepict";

    public static final String FUNC_GETDERIVATIONTOKENID = "getDerivationTokenId";

    public static final String FUNC_GETINVESTOR = "getInvestor";

    public static final String FUNC_GETINVESTORNUM = "getInvestorNum";

    public static final String FUNC_GETNUM = "getNum";

    public static final String FUNC_GETORIGINALOWNER = "getOriginalOwner";

    public static final String FUNC_GETOWNERBYPROJECTTOKENIDANDINDEX = "getOwnerByProjectTokenIdAndIndex";

    public static final String FUNC_GETOWNERNUM = "getOwnerNum";

    public static final String FUNC_GETPRESENTOWNER = "getPresentOwner";

    public static final String FUNC_GETSOLDNUM = "getSoldNum";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_GETTOTALINVESTMONEY = "getTotalInvestMoney";

    public static final String FUNC_GETWORKCONTRACT = "getWorkContract";

    public static final String FUNC_GETWORKID = "getWorkId";

    public static final String FUNC_GETWORKNUM = "getWorkNum";

    public static final String FUNC_JUDGE = "judge";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_RESETTOKENID = "resetTokenId";

    public static final String FUNC_SETDEPICT = "setDepict";

    public static final String FUNC_SETDERIVATIONTOKENID = "setDerivationTokenId";

    public static final String FUNC_SETINVESTMONEYFORPROJECT = "setInvestMoneyForProject";

    public static final String FUNC_SETINVESTOR = "setInvestor";

    public static final String FUNC_SETOWNERSHIPBYPROJECTTOKENID = "setOwnerShipByProjectTokenId";

    public static final String FUNC_SETPRESENTOWNER = "setPresentOwner";

    public static final String FUNC_SETTOKENADDRESS = "setTokenAddress";

    public static final String FUNC_SETWORKCONTRACT = "setWorkContract";

    public static final String FUNC_SETWORKID = "setWorkId";

    public static final String FUNC_SETWORKNUM = "setWorkNum";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event AFTERPROJECCTRELEASE_EVENT = new Event("afterProjecctRelease", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected Project(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Project(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Project(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Project(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AfterProjecctReleaseEventResponse> getAfterProjecctReleaseEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AFTERPROJECCTRELEASE_EVENT, transactionReceipt);
        ArrayList<AfterProjecctReleaseEventResponse> responses = new ArrayList<AfterProjecctReleaseEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AfterProjecctReleaseEventResponse typedResponse = new AfterProjecctReleaseEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._contractAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._from = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AfterProjecctReleaseEventResponse> afterProjecctReleaseEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AfterProjecctReleaseEventResponse>() {
            @Override
            public AfterProjecctReleaseEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AFTERPROJECCTRELEASE_EVENT, log);
                AfterProjecctReleaseEventResponse typedResponse = new AfterProjecctReleaseEventResponse();
                typedResponse.log = log;
                typedResponse._id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._contractAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._from = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AfterProjecctReleaseEventResponse> afterProjecctReleaseEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AFTERPROJECCTRELEASE_EVENT));
        return afterProjecctReleaseEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addInvestorNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDINVESTORNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addOneOwnerNum(BigInteger _projectTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDONEOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addSoldNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDSOLDNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addTotalInvestMoney(BigInteger _projectTokenId, BigInteger _money) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDTOTALINVESTMONEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addWork(BigInteger _projectTokenId, BigInteger _workTokenId, String _workContract) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDWORK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId), 
                new org.web3j.abi.datatypes.Address(160, _workContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addWorkNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDWORKNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getDepict(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDEPICT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getDerivationTokenId(BigInteger _projectTokenId, BigInteger _index, String _Contract) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETDERIVATIONTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index), 
                new org.web3j.abi.datatypes.Address(160, _Contract)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getInvestor(BigInteger _projectTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINVESTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getInvestorNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINVESTORNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getOriginalOwner(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORIGINALOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOwnerByProjectTokenIdAndIndex(BigInteger _projectTokenID, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERBYPROJECTTOKENIDANDINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenID), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getOwnerNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPresentOwner(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getSoldNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSOLDNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<BigInteger> getTotalInvestMoney(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALINVESTMONEY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getWorkContract(BigInteger _rightTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getWorkId(BigInteger _rightTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWORKID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getWorkNum(BigInteger _projectTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETWORKNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> judge(BigInteger _projectTokenId, String _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_JUDGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> resetTokenId(BigInteger _projectTokenId, String _Contract, BigInteger _TokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RESETTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _Contract), 
                new org.web3j.abi.datatypes.generated.Uint256(_TokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDepict(BigInteger _projectTokenId, String _depict) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETDEPICT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Utf8String(_depict)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setDerivationTokenId(BigInteger _projectTokenId, String _Contract, BigInteger _TokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETDERIVATIONTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _Contract), 
                new org.web3j.abi.datatypes.generated.Uint256(_TokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setInvestMoneyForProject(BigInteger _projectTokenId, String _investor, BigInteger _investMoney) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETINVESTMONEYFORPROJECT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _investor), 
                new org.web3j.abi.datatypes.generated.Uint256(_investMoney)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setInvestor(BigInteger _projectTokenId, String _investor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETINVESTOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _investor)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwnerShipByProjectTokenId(BigInteger _projectTokenId, String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETOWNERSHIPBYPROJECTTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPresentOwner(BigInteger _projectTokenId, String _presentOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _presentOwner)), 
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

    public RemoteFunctionCall<TransactionReceipt> setWorkContract(BigInteger _projectTokenId, String _workContract) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _workContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWorkId(BigInteger _projectTokenId, BigInteger _workTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETWORKID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWorkNum(BigInteger _projectTokenId, BigInteger _num) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETWORKNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_num)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(BigInteger _projectTokenId, String _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_projectTokenId), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Project load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Project(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Project load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Project(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Project load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Project(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Project load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Project(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Project> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Project.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Project> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Project.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Project> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Project.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Project> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Project.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AfterProjecctReleaseEventResponse extends BaseEventResponse {
        public BigInteger _id;

        public String _contractAddress;

        public String _from;
    }
}
