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
public class Shares extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50612422806100206000396000f3fe608060405234801561001057600080fd5b50600436106102475760003560e01c80637b161e711161013b578063be471a71116100b8578063f297be661161007c578063f297be6614610e34578063f6f2f4dc14610e8c578063f91236df14610f0e578063fd37a05614610f5c578063ff77ac1c14610fe857610247565b8063be471a7114610c08578063c509da2c14610c76578063caaf7abf14610cf8578063db4fc60f14610d70578063eb0b490f14610dd257610247565b806399721e92116100ff57806399721e9214610a6c5780639e6393c014610ae45780639fabf02214610b125780639fd89bf514610b4a578063aeb33b3414610ba257610247565b80637b161e71146108ba57806382784455146109285780638dc194e41461096c578063924271c5146109da57806395eff82514610a2857610247565b806339884889116101c957806360ef20b21161018d57806360ef20b2146107025780636476cfd61461074457806367ccdf38146107d05780636fad70e41461083e57806375d185af1461088c57610247565b8063398848891461054457806340fb732e1461059c5780634c90efe4146106145780634faf6a0c14610632578063583b3942146106b457610247565b806320a32fcb1161021057806320a32fcb14610380578063287e96c1146103ec5780632d43026e1461043a57806334a920eb14610488578063356fb98a146104f657610247565b8062f07f5b1461024c578063010a38f514610284578063039383e6146102a257806308bb4104146102f05780630ab51ee214610332575b600080fd5b6102826004803603604081101561026257600080fd5b81019080803590602001909291908035906020019092919050505061102a565b005b61028c611046565b6040518082815260200191505060405180910390f35b6102ee600480360360408110156102b857600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061105c565b005b61031c6004803603602081101561030657600080fd5b81019080803590602001909291905050506111f5565b6040518082815260200191505060405180910390f35b61037e6004803603604081101561034857600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611212565b005b6103d66004803603606081101561039657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611290565b6040518082815260200191505060405180910390f35b6104386004803603604081101561040257600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611312565b005b6104866004803603604081101561045057600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611367565b005b6104b46004803603602081101561049e57600080fd5b81019080803590602001909291905050506113cb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6105426004803603604081101561050c57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611408565b005b6105866004803603602081101561055a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061145e565b6040518082815260200191505060405180910390f35b6105d2600480360360408110156105b257600080fd5b8101908080359060200190929190803590602001909291905050506114a7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61061c61150f565b6040518082815260200191505060405180910390f35b61069e6004803603606081101561064857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611525565b6040518082815260200191505060405180910390f35b610700600480360360408110156106ca57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611561565b005b61072e6004803603602081101561071857600080fd5b8101908080359060200190929190505050611653565b6040518082815260200191505060405180910390f35b6107ba6004803603608081101561075a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611670565b6040518082815260200191505060405180910390f35b6107fc600480360360208110156107e657600080fd5b8101908080359060200190929190505050611731565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61088a6004803603604081101561085457600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061176d565b005b6108b8600480360360208110156108a257600080fd5b81019080803590602001909291905050506117c3565b005b6108e6600480360360208110156108d057600080fd5b81019080803590602001909291905050506117ea565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61096a6004803603602081101561093e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611827565b005b6109986004803603602081101561098257600080fd5b810190808035906020019092919050505061187a565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b610a26600480360360408110156109f057600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506118b7565b005b610a6a60048036036020811015610a3e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061190d565b005b610ae260048036036080811015610a8257600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291908035906020019092919050505061195f565b005b610b1060048036036020811015610afa57600080fd5b81019080803590602001909291905050506119f7565b005b610b4860048036036040811015610b2857600080fd5b810190808035906020019092919080359060200190929190505050611a1d565b005b610ba060048036036060811015610b6057600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611a39565b005b610bee60048036036040811015610bb857600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611acb565b604051808215151515815260200191505060405180910390f35b610c7460048036036060811015610c1e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611b83565b005b610ce260048036036060811015610c8c57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611c25565b6040518082815260200191505060405180910390f35b610d6e60048036036080811015610d0e57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611c7e565b005b610dbc60048036036040811015610d8657600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611d42565b6040518082815260200191505060405180910390f35b610e1e60048036036040811015610de857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611d9d565b6040518082815260200191505060405180910390f35b610e8a60048036036060811015610e4a57600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919080359060200190929190505050611e54565b005b610ef860048036036060811015610ea257600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050612005565b6040518082815260200191505060405180910390f35b610f5a60048036036040811015610f2457600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061209e565b005b610fd260048036036080811015610f7257600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001909291905050506120e6565b6040518082815260200191505060405180910390f35b61101460048036036020811015610ffe57600080fd5b810190808035906020019092919050505061213d565b6040518082815260200191505060405180910390f35b80600b6000848152602001908152602001600020819055505050565b6000600160008154600101919050819055905090565b611065826113cb565b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461109c57600080fd5b6110a68282611408565b60006110b1836117ea565b905060006110be846111f5565b90503373ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16146111c7576110fc8361190d565b61110883838387611c7e565b6000611116338484886120e6565b90506000600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008481526020019081526020016000206000838152602001908152602001600020819055506111c533611827565b505b60006111d38383611d9d565b90506111df8185611acb565b6111ee576111ed8185611212565b5b5050505050565b600060056000838152602001908152602001600020549050919050565b600061121d8361213d565b905081600d6000858152602001908152602001600020600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061128b836119f7565b505050565b600061129c8484611d42565b82106112a757600080fd5b600960008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600084815260200190815260200160002060008381526020019081526020016000205490509392505050565b8060008084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600a60008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828152602001908152602001600020600081548092919060010191905055505050565b60006003600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b806003600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6000600660008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000806114b38461213d565b90508083106114c157600080fd5b600d6000858152602001908152602001600020600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691505092915050565b6000600260008154600101919050819055905090565b600080611533858585611c25565b905060006115418585611d42565b9050600081606484028161155157fe5b0490508093505050509392505050565b3373ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff166334a920eb836040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b1580156115c957600080fd5b505afa1580156115dd573d6000803e3d6000fd5b505050506040513d60208110156115f357600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff161461162457600080fd5b600061162e61150f565b905061163a818461176d565b611644818361102a565b61164e8133611212565b505050565b6000600b6000838152602001908152602001600020549050919050565b600061167d858585612005565b821061168857600080fd5b600860008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008481526020019081526020016000206000838152602001908152602001600020549050949350505050565b600080600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600c600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600e6000828152602001908152602001600020600081548092919060019003919050555050565b60006004600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600660008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600081548092919060019003919050555050565b6000600c600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b806004600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600660008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000815480929190600101919050555050565b80600760008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008481526020019081526020016000208190555050505050565b600e60008281526020019081526020016000206000815480929190600101919050555050565b8060056000848152602001908152602001600020819055505050565b80600960008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008481526020019081526020016000206000611a968686611d42565b815260200190815260200160002081905550611ab28383611367565b611abc81846118b7565b611ac68183611a1d565b505050565b6000806000611ad98561213d565b90506000809050600092505b81831015611b77578473ffffffffffffffffffffffffffffffffffffffff16600d6000888152602001908152602001600020600085815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415611b6a57600190505b8280600101935050611ae5565b80935050505092915050565b600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082815260200190815260200160002060008154809291906001019190505550505050565b600080611c33858585612005565b9050600080905060008090505b82811015611c71576000611c5688888885611670565b14611c645781806001019250505b8080600101915050611c40565b5080925050509392505050565b6000611c8b858585612005565b905081600860008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000858152602001908152602001600020600083815260200190815260200160002081905550611d3b858585611b83565b5050505050565b6000600a60008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600083815260200190815260200160002054905092915050565b6000806000809050600191505b6002548211611e495783600b600084815260200190815260200160002054148015611e3357508473ffffffffffffffffffffffffffffffffffffffff16600c600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16145b15611e3c578190505b8180600101925050611daa565b809250505092915050565b3373ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff166334a920eb846040518263ffffffff1660e01b81526004018082815260200191505060206040518083038186803b158015611ebc57600080fd5b505afa158015611ed0573d6000803e3d6000fd5b505050506040513d6020811015611ee657600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1614611f1757600080fd5b6000611f238484611d42565b14611f2d57600080fd5b611f378383611561565b600080600091505b82821015611ffe57611f4f611046565b905060003082604051611f619061215a565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f080158015611fba573d6000803e3d6000fd5b509050611fc78282611312565b611fd182876118b7565b611fdb8286611a1d565b611fe6868684611a39565b611ff08233611408565b508180600101925050611f3f565b5050505050565b6000600760008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008381526020019081526020016000205490509392505050565b80600660008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b6000806120f4868686612005565b905060008090505b8181101561212e578361211188888885611670565b1415612121578392505050612135565b80806001019150506120fc565b5060009150505b949350505050565b6000600e6000838152602001908152602001600020549050919050565b610286806121688339019056fe608060405234801561001057600080fd5b506040516102863803806102868339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060018190555050506101e0806100a66000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063010a38f514610051578063174ae4431461006f5780634c901b8c146100b3578063c929ccf3146100fd575b600080fd5b61005961012b565b6040518082815260200191505060405180910390f35b6100b16004803603602081101561008557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610135565b005b6100bb610178565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6101296004803603602081101561011357600080fd5b81019080803590602001909291905050506101a1565b005b6000600154905090565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b806001819055505056fea265627a7a72315820b0be30a30eef109e872a9a4a7a3dfb06e8dfc06dd29de5ada978e4db1414744a64736f6c63430005110032a265627a7a723158206df9c9607a7e8037f719215a10a4ac1a8cc8ad97e0b00a11302fa51791fb370e64736f6c63430005110032";

    public static final String FUNC_ADDACCOUNTTOSHARENUM = "addAccountToShareNum";

    public static final String FUNC_ADDACCOUNTTOWORKSHARENUM = "addAccountToWorkShareNum";

    public static final String FUNC_ADDSHAREHOLDERSMEMBERNUM = "addShareHoldersMemberNum";

    public static final String FUNC_ADDWORKSHARENUM = "addWorkShareNum";

    public static final String FUNC_DECREASEACCOUNTTOSHARENUM = "decreaseAccountToShareNum";

    public static final String FUNC_DECREASESHAREHOLDESMEMBERNUM = "decreaseShareHoldesMemberNum";

    public static final String FUNC_GETACCOUNTSHAREPROPORTION = "getAccountShareProportion";

    public static final String FUNC_GETACCOUNTTOSHARENUM = "getAccountToShareNum";

    public static final String FUNC_GETACCOUNTTOWORKSHARE = "getAccountToWorkShare";

    public static final String FUNC_GETACCOUNTTOWORKSHARENUM = "getAccountToWorkShareNum";

    public static final String FUNC_GETACCOUNTWORKREMAININGSHARENUM = "getAccountWorkRemainingShareNum";

    public static final String FUNC_GETPRESENTOWNER = "getPresentOwner";

    public static final String FUNC_GETSHAREHOLDERTOKENID = "getShareHolderTokenId";

    public static final String FUNC_GETSHAREHOLDERSMEMBER = "getShareHoldersMember";

    public static final String FUNC_GETSHAREHOLDERSMEMBERNUM = "getShareHoldersMemberNum";

    public static final String FUNC_GETSHAREHOLDERSTOWORK = "getShareHoldersToWork";

    public static final String FUNC_GETSHAREHOLDERSTOWORKCONTRACT = "getShareHoldersToWorkContract";

    public static final String FUNC_GETSHARETOWORK = "getShareToWork";

    public static final String FUNC_GETSHARETOWORKCONTRACT = "getShareToWorkContract";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_GETWORKSHARENUM = "getWorkShareNum";

    public static final String FUNC_GETWORKTOSHARE = "getWorkToShare";

    public static final String FUNC_JUDGEMEMBER = "judgeMember";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_RELEASESHAREHOLDERS = "releaseShareHolders";

    public static final String FUNC_SEARCHINDEXBYSHARE = "searchIndexByShare";

    public static final String FUNC_SEARCHSHAREHOLDERSIDBYWORKID = "searchShareHoldersIdByWorkId";

    public static final String FUNC_SETACCOUNTTOSHARENUM = "setAccountToShareNum";

    public static final String FUNC_SETACCOUNTTOWORKSHARE = "setAccountToWorkShare";

    public static final String FUNC_SETACCOUNTTOWORKSHARENUM = "setAccountToWorkShareNum";

    public static final String FUNC_SETPRESENTOWNER = "setPresentOwner";

    public static final String FUNC_SETSHAREHOLDERSMEMBER = "setShareHoldersMember";

    public static final String FUNC_SETSHAREHOLDERSTOWORK = "setShareHoldersToWork";

    public static final String FUNC_SETSHAREHOLDERSTOWORKCONTRACT = "setShareHoldersToWorkContract";

    public static final String FUNC_SETSHARETOWORK = "setShareToWork";

    public static final String FUNC_SETSHARETOWORKCONTRACT = "setShareToWorkContract";

    public static final String FUNC_SETTOKENADDRESS = "setTokenAddress";

    public static final String FUNC_SETWORKTOSHARE = "setWorkToShare";

    public static final String FUNC_TRANSFERSHARE = "transferShare";

    @Deprecated
    protected Shares(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Shares(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Shares(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Shares(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> addAccountToShareNum(String _account) {
        final Function function = new Function(
                FUNC_ADDACCOUNTTOSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addAccountToWorkShareNum(String _account, String _workContract, BigInteger _workId) {
        final Function function = new Function(
                FUNC_ADDACCOUNTTOWORKSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addShareHoldersMemberNum(BigInteger _shareHoldersID) {
        final Function function = new Function(
                FUNC_ADDSHAREHOLDERSMEMBERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addWorkShareNum(String _workContract, BigInteger _workId) {
        final Function function = new Function(
                FUNC_ADDWORKSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAccountToShareNum(String _account) {
        final Function function = new Function(
                FUNC_DECREASEACCOUNTTOSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseShareHoldesMemberNum(BigInteger _shareHoldersID) {
        final Function function = new Function(
                FUNC_DECREASESHAREHOLDESMEMBERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getAccountShareProportion(String _account, String _workContract, BigInteger _workId) {
        final Function function = new Function(FUNC_GETACCOUNTSHAREPROPORTION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAccountToShareNum(String _account) {
        final Function function = new Function(FUNC_GETACCOUNTTOSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAccountToWorkShare(String _account, String _workContract, BigInteger _workId, BigInteger _index) {
        final Function function = new Function(FUNC_GETACCOUNTTOWORKSHARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAccountToWorkShareNum(String _account, String _workContract, BigInteger _workId) {
        final Function function = new Function(FUNC_GETACCOUNTTOWORKSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAccountWorkRemainingShareNum(String _account, String _workContract, BigInteger _workId) {
        final Function function = new Function(FUNC_GETACCOUNTWORKREMAININGSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPresentOwner(BigInteger _shareTokenID) {
        final Function function = new Function(FUNC_GETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> getShareHolderTokenId() {
        final Function function = new Function(
                FUNC_GETSHAREHOLDERTOKENID, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getShareHoldersMember(BigInteger _shareHoldersID, BigInteger _index) {
        final Function function = new Function(FUNC_GETSHAREHOLDERSMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getShareHoldersMemberNum(BigInteger _shareHoldersID) {
        final Function function = new Function(FUNC_GETSHAREHOLDERSMEMBERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getShareHoldersToWork(BigInteger _shareHoldersID) {
        final Function function = new Function(FUNC_GETSHAREHOLDERSTOWORK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getShareHoldersToWorkContract(BigInteger _shareHoldersID) {
        final Function function = new Function(FUNC_GETSHAREHOLDERSTOWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getShareToWork(BigInteger _shareTokenID) {
        final Function function = new Function(FUNC_GETSHARETOWORK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getShareToWorkContract(BigInteger _shareTokenID) {
        final Function function = new Function(FUNC_GETSHARETOWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID)), 
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

    public RemoteFunctionCall<BigInteger> getWorkShareNum(String _workContract, BigInteger _workId) {
        final Function function = new Function(FUNC_GETWORKSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getWorkToShare(String _workContract, BigInteger _workId, BigInteger _index) {
        final Function function = new Function(FUNC_GETWORKTOSHARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> judgeMember(BigInteger _shareHoldersId, String _member) {
        final Function function = new Function(FUNC_JUDGEMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersId), 
                new org.web3j.abi.datatypes.Address(160, _member)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String _workContract, BigInteger _workId, BigInteger _num) {
        final Function function = new Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_num)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> releaseShareHolders(String _workContract, BigInteger _workId) {
        final Function function = new Function(
                FUNC_RELEASESHAREHOLDERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> searchIndexByShare(String _account, String _workContract, BigInteger _workId, BigInteger _shareId) {
        final Function function = new Function(FUNC_SEARCHINDEXBYSHARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_shareId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> searchShareHoldersIdByWorkId(String _workContract, BigInteger _workTokenId) {
        final Function function = new Function(FUNC_SEARCHSHAREHOLDERSIDBYWORKID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountToShareNum(String _account, BigInteger _shareNum) {
        final Function function = new Function(
                FUNC_SETACCOUNTTOSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.generated.Uint256(_shareNum)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountToWorkShare(String _account, String _workContract, BigInteger _workId, BigInteger _shareId) {
        final Function function = new Function(
                FUNC_SETACCOUNTTOWORKSHARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_shareId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAccountToWorkShareNum(String _account, String _workContract, BigInteger _workId, BigInteger _shareNum) {
        final Function function = new Function(
                FUNC_SETACCOUNTTOWORKSHARENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _account), 
                new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_shareNum)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPresentOwner(BigInteger _shareTokenID, String _presentOwner) {
        final Function function = new Function(
                FUNC_SETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID), 
                new org.web3j.abi.datatypes.Address(160, _presentOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShareHoldersMember(BigInteger _shareHoldersID, String _member) {
        final Function function = new Function(
                FUNC_SETSHAREHOLDERSMEMBER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID), 
                new org.web3j.abi.datatypes.Address(160, _member)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShareHoldersToWork(BigInteger _shareHoldersID, BigInteger _workId) {
        final Function function = new Function(
                FUNC_SETSHAREHOLDERSTOWORK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShareHoldersToWorkContract(BigInteger _shareHoldersID, String _workContract) {
        final Function function = new Function(
                FUNC_SETSHAREHOLDERSTOWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareHoldersID), 
                new org.web3j.abi.datatypes.Address(160, _workContract)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShareToWork(BigInteger _shareTokenID, BigInteger _workId) {
        final Function function = new Function(
                FUNC_SETSHARETOWORK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShareToWorkContract(BigInteger _shareTokenID, String _workContract) {
        final Function function = new Function(
                FUNC_SETSHARETOWORKCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID), 
                new org.web3j.abi.datatypes.Address(160, _workContract)), 
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

    public RemoteFunctionCall<TransactionReceipt> setWorkToShare(String _workContract, BigInteger _workId, BigInteger _shareTokenID) {
        final Function function = new Function(
                FUNC_SETWORKTOSHARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _workContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_workId), 
                new org.web3j.abi.datatypes.generated.Uint256(_shareTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferShare(BigInteger _shareId, String _to) {
        final Function function = new Function(
                FUNC_TRANSFERSHARE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_shareId), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Shares load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Shares(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Shares load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Shares(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Shares load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Shares(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Shares load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Shares(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Shares> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Shares.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Shares> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Shares.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Shares> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Shares.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Shares> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Shares.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
