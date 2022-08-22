package com.example.demo.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
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
public class CommodifyRight extends Contract {
    public static final String BINARY = "6080604052604051806080016040528060438152602001620014946043913960009080519060200190620000359291906200004a565b503480156200004357600080fd5b50620000f9565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200008d57805160ff1916838001178555620000be565b82800160010185558215620000be579182015b82811115620000bd578251825591602001919060010190620000a0565b5b509050620000cd9190620000d1565b5090565b620000f691905b80821115620000f2576000816000905550600101620000d8565b5090565b90565b61138b80620001096000396000f3fe608060405234801561001057600080fd5b506004361061012c5760003560e01c806390a4c950116100ad578063c6722ce211610071578063c6722ce214610631578063df08634214610669578063e15d4e03146106ef578063eb65fe2314610733578063ec8433c5146107ab5761012c565b806390a4c9501461044d57806392b84c11146104c5578063a49844bf14610533578063b0d48edc14610581578063b7760c8f146105e35761012c565b8063452788de116100f4578063452788de1461029b5780634bf7d656146102dd5780634eeba5be1461030b57806367ccdf38146103795780637494ad81146103e75761012c565b8063010a38f51461013157806307cb72841461014f578063287e96c11461019157806334a920eb146101df578063356fb98a1461024d575b600080fd5b6101396107f9565b6040518082815260200191505060405180910390f35b61017b6004803603602081101561016557600080fd5b810190808035906020019092919050505061080f565b6040518082815260200191505060405180910390f35b6101dd600480360360408110156101a757600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061082c565b005b61020b600480360360208110156101f557600080fd5b8101908080359060200190929190505050610882565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6102996004803603604081101561026357600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108bf565b005b6102c7600480360360208110156102b157600080fd5b8101908080359060200190929190505050610915565b6040518082815260200191505060405180910390f35b610309600480360360208110156102f357600080fd5b8101908080359060200190929190505050610932565b005b6103376004803603602081101561032157600080fd5b8101908080359060200190929190505050610958565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6103a56004803603602081101561038f57600080fd5b81019080803590602001909291905050506109a6565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610433600480360360408110156103fd57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506109e3565b604051808215151515815260200191505060405180910390f35b6104836004803603604081101561046357600080fd5b810190808035906020019092919080359060200190929190505050610a65565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6104f1600480360360208110156104db57600080fd5b8101908080359060200190929190505050610ac8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61057f6004803603604081101561054957600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610b05565b005b6105cd6004803603604081101561059757600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610b7d565b6040518082815260200191505060405180910390f35b61062f600480360360408110156105f957600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c03565b005b6106676004803603604081101561064757600080fd5b810190808035906020019092919080359060200190929190505050610c72565b005b6106d56004803603606081101561067f57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c8e565b604051808215151515815260200191505060405180910390f35b6107316004803603602081101561070557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610cfc565b005b6107a96004803603608081101561074957600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610e45565b005b6107f7600480360360408110156107c157600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061106d565b005b6000600160008154600101919050819055905090565b600060066000838152602001908152602001600020549050919050565b806002600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60006007600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b806007600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600060036000838152602001908152602001600020549050919050565b600360008281526020019081526020016000206000815480929190600101919050555050565b600060046000838152602001908152602001600020600080815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60006002600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600080600090506000806109f686610915565b905060008092505b81831015610a5857610a108784610a65565b90508073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff161415610a4b57600193505b82806001019350506109fe565b8394505050505092915050565b6000610a7083610915565b8210610a7b57600080fd5b60046000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b60006005600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600460008481526020019081526020016000206000610b2485610915565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610b7982610932565b5050565b60008060019050600190505b6001548111610bf7578373ffffffffffffffffffffffffffffffffffffffff16610bb282610ac8565b73ffffffffffffffffffffffffffffffffffffffff16148015610bdc575082610bda8261080f565b145b15610bea5780915050610bfd565b8080600101915050610b89565b60009150505b92915050565b610c0c82610882565b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614610c4357600080fd5b60001515610c5183836109e3565b15151415610c6457610c638282610b05565b5b610c6e82826108bf565b5050565b8060066000848152602001908152602001600020819055505050565b600080610c9b8585610b7d565b9050600081118015610ce057508273ffffffffffffffffffffffffffffffffffffffff16610cc882610882565b73ffffffffffffffffffffffffffffffffffffffff16145b15610cef576001915050610cf5565b60009150505b9392505050565b8073ffffffffffffffffffffffffffffffffffffffff166323fabea43360006040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818154600181600116156101000203166002900481526020019150805460018160011615610100020316600290048015610de55780601f10610dba57610100808354040283529160200191610de5565b820191906000526020600020905b815481529060010190602001808311610dc857829003601f168201915b50509350505050602060405180830381600087803b158015610e0657600080fd5b505af1158015610e1a573d6000803e3d6000fd5b505050506040513d6020811015610e3057600080fd5b81019080805190602001909291905050505050565b60008173ffffffffffffffffffffffffffffffffffffffff1663a3081c2a33876040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060206040518083038186803b158015610ecc57600080fd5b505afa158015610ee0573d6000803e3d6000fd5b505050506040513d6020811015610ef657600080fd5b8101908080519060200190929190505050905080610f1357600080fd5b6000610f1d6107f9565b905060003082604051610f2f906110c3565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f080158015610f88573d6000803e3d6000fd5b509050610f95828261082c565b610f9f8233610b05565b610fa9828761106d565b610fb38286610c72565b610fbd82336108bf565b8573ffffffffffffffffffffffffffffffffffffffff16630beb19af8630856040518463ffffffff1660e01b8152600401808481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050600060405180830381600087803b15801561104c57600080fd5b505af1158015611060573d6000803e3d6000fd5b5050505050505050505050565b806005600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b610286806110d18339019056fe608060405234801561001057600080fd5b506040516102863803806102868339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060018190555050506101e0806100a66000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063010a38f514610051578063174ae4431461006f5780634c901b8c146100b3578063c929ccf3146100fd575b600080fd5b61005961012b565b6040518082815260200191505060405180910390f35b6100b16004803603602081101561008557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610135565b005b6100bb610178565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6101296004803603602081101561011357600080fd5b81019080803590602001909291905050506101a1565b005b6000600154905090565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b806001819055505056fea265627a7a72315820b0be30a30eef109e872a9a4a7a3dfb06e8dfc06dd29de5ada978e4db1414744a64736f6c63430005110032a265627a7a72315820c75222ebe24433a03579f1c4549cf2f418a7fa014f2ecd5b3bc954907a49fc7964736f6c634300051100324d65726368616e646973696e6720706f7765722c2074686520706f77657220746f207075626c6973682070726f64756374732072656c6174656420746f20776f726b73";

    public static final String FUNC_ADDONEOWNERNUM = "addOneOwnerNum";

    public static final String FUNC_GETORIGINALOWNER = "getOriginalOwner";

    public static final String FUNC_GETOWNERBYRIGHTTOKENIDANDINDEX = "getOwnerByRightTokenIdAndIndex";

    public static final String FUNC_GETOWNERNUM = "getOwnerNum";

    public static final String FUNC_GETPRESENTOWNER = "getPresentOwner";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_GETWORKCONTRACT = "getWorkContract";

    public static final String FUNC_GETWORKID = "getWorkId";

    public static final String FUNC_JUDGE = "judge";

    public static final String FUNC_JUDGERIGHTTOKENID = "judgeRightTokenId";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_SEARCHRIGHTTOKENID = "searchRightTokenId";

    public static final String FUNC_SENDPROPOSALREQUEST = "sendProposalRequest";

    public static final String FUNC_SETOWNERSHIPBYRIGHTTOKENID = "setOwnerShipByRightTokenId";

    public static final String FUNC_SETPRESENTOWNER = "setPresentOwner";

    public static final String FUNC_SETTOKENADDRESS = "setTokenAddress";

    public static final String FUNC_SETWORKCONTRACT = "setWorkContract";

    public static final String FUNC_SETWORKID = "setWorkId";

    public static final String FUNC_TRANSFER = "transfer";

    @Deprecated
    protected CommodifyRight(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CommodifyRight(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CommodifyRight(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CommodifyRight(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addOneOwnerNum(BigInteger _rightTokenID) {
        final Function function = new Function(
                FUNC_ADDONEOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOriginalOwner(BigInteger _rightTokenId) {
        final Function function = new Function(FUNC_GETORIGINALOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOwnerByRightTokenIdAndIndex(BigInteger _rightTokenID, BigInteger _index) {
        final Function function = new Function(FUNC_GETOWNERBYRIGHTTOKENIDANDINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenID), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getOwnerNum(BigInteger _rightTokenId) {
        final Function function = new Function(FUNC_GETOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPresentOwner(BigInteger _rightTokenId) {
        final Function function = new Function(FUNC_GETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getTokenAddress(BigInteger _ipTokenID) {
        final Function function = new Function(FUNC_GETTOKENADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getTokenId() {
        final Function function = new Function(
                FUNC_GETTOKENID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getWorkContract(BigInteger _rightTokenId) {
        final Function function = new Function(FUNC_GETWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getWorkId(BigInteger _rightTokenId) {
        final Function function = new Function(FUNC_GETWORKID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> judge(BigInteger _rightTokenId, String _to) {
        final Function function = new Function(FUNC_JUDGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> judgeRightTokenId(String _workContract, BigInteger _workTokenId, String _sender) {
        final Function function = new Function(FUNC_JUDGERIGHTTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId), 
                new org.web3j.abi.datatypes.Address(160, _sender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release(BigInteger _proposalId, String _workContract, BigInteger _workTokenId, String _committee) {
        final Function function = new Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId), 
                new org.web3j.abi.datatypes.Address(160, _committee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> searchRightTokenId(String _workContract, BigInteger _workTokenId) {
        final Function function = new Function(FUNC_SEARCHRIGHTTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sendProposalRequest(String _committee) {
        final Function function = new Function(
                FUNC_SENDPROPOSALREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _committee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwnerShipByRightTokenId(BigInteger _rightTokenId, String _owner) {
        final Function function = new Function(
                FUNC_SETOWNERSHIPBYRIGHTTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPresentOwner(BigInteger _rightTokenId, String _owner) {
        final Function function = new Function(
                FUNC_SETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setTokenAddress(BigInteger _ipTokenId, String _tokenAddress) {
        final Function function = new Function(
                FUNC_SETTOKENADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWorkContract(BigInteger _rightTokenId, String _workContract) {
        final Function function = new Function(
                FUNC_SETWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.Address(160, _workContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setWorkId(BigInteger _rightTokenId, BigInteger _workTokenId) {
        final Function function = new Function(
                FUNC_SETWORKID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(BigInteger _rightTokenId, String _to) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_rightTokenId), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CommodifyRight load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CommodifyRight(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CommodifyRight load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CommodifyRight(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CommodifyRight load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CommodifyRight(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CommodifyRight load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CommodifyRight(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CommodifyRight> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CommodifyRight.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CommodifyRight> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CommodifyRight.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CommodifyRight> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CommodifyRight.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CommodifyRight> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CommodifyRight.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
