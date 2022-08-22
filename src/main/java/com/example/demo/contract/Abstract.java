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
public class Abstract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50612356806100206000396000f3fe6080604052600436106101f85760003560e01c80637d60ea6d1161010d578063b8aeb425116100a0578063e75722301161006f578063e757223014610e9a578063efef39a114610ee9578063f7d9757714610f17578063f8548e3614610f5c578063ffb1e47b14610fc1576101f8565b8063b8aeb42514610d30578063c641c7cb14610d6b578063e144ef9c14610de6578063e15d4e0314610e35576101f8565b8063a5936b56116100dc578063a5936b5614610abd578063a61490aa14610b42578063a6fbe5b314610b9b578063a770106114610cab576101f8565b80637d60ea6d146108e6578063922f0371146109415780639b85729c146109f5578063a155ec0e14610a4e576101f8565b806334a920eb1161019057806350697f531161015f57806350697f531461069c5780635a3b4bdc1461076e57806367ccdf38146107a95780636f158eaa146108245780637494ad8114610873576101f8565b806334a920eb1461053c578063356fb98a146105b7578063452788de146106125780634bf7d65614610661576101f8565b8063207c76d0116101cc578063207c76d014610377578063253a4997146103b2578063287e96c11461049257806334a8de33146104ed576101f8565b80621616f9146101fd578063010a38f5146102825780630beb19af146102ad5780631bc69b8414610312575b600080fd5b34801561020957600080fd5b506102406004803603604081101561022057600080fd5b810190808035906020019092919080359060200190929190505050611026565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561028e57600080fd5b50610297611089565b6040518082815260200191505060405180910390f35b3480156102b957600080fd5b50610310600480360360608110156102d057600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061109e565b005b34801561031e57600080fd5b506103756004803603606081101561033557600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611185565b005b34801561038357600080fd5b506103b06004803603602081101561039a57600080fd5b810190808035906020019092919050505061126c565b005b3480156103be57600080fd5b50610478600480360360208110156103d557600080fd5b81019080803590602001906401000000008111156103f257600080fd5b82018360208201111561040457600080fd5b8035906020019184600183028401116401000000008311171561042657600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050611293565b604051808215151515815260200191505060405180910390f35b34801561049e57600080fd5b506104eb600480360360408110156104b557600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061134f565b005b3480156104f957600080fd5b506105266004803603602081101561051057600080fd5b81019080803590602001909291905050506113a5565b6040518082815260200191505060405180910390f35b34801561054857600080fd5b506105756004803603602081101561055f57600080fd5b81019080803590602001909291905050506113c2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156105c357600080fd5b50610610600480360360408110156105da57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506113ff565b005b34801561061e57600080fd5b5061064b6004803603602081101561063557600080fd5b8101908080359060200190929190505050611455565b6040518082815260200191505060405180910390f35b34801561066d57600080fd5b5061069a6004803603602081101561068457600080fd5b8101908080359060200190929190505050611472565b005b3480156106a857600080fd5b5061076c600480360360408110156106bf57600080fd5b8101908080359060200190929190803590602001906401000000008111156106e657600080fd5b8201836020820111156106f857600080fd5b8035906020019184600183028401116401000000008311171561071a57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f820116905080830192505050505050509192919290505050611498565b005b34801561077a57600080fd5b506107a76004803603602081101561079157600080fd5b81019080803590602001909291905050506114c4565b005b3480156107b557600080fd5b506107e2600480360360208110156107cc57600080fd5b81019080803590602001909291905050506114ea565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561083057600080fd5b5061085d6004803603602081101561084757600080fd5b8101908080359060200190929190505050611527565b6040518082815260200191505060405180910390f35b34801561087f57600080fd5b506108cc6004803603604081101561089657600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611544565b604051808215151515815260200191505060405180910390f35b3480156108f257600080fd5b5061093f6004803603604081101561090957600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506115c6565b005b34801561094d57600080fd5b5061097a6004803603602081101561096457600080fd5b810190808035906020019092919050505061163e565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156109ba57808201518184015260208101905061099f565b50505050905090810190601f1680156109e75780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b348015610a0157600080fd5b50610a3860048036036040811015610a1857600080fd5b8101908080359060200190929190803590602001909291905050506116f3565b6040518082815260200191505060405180910390f35b348015610a5a57600080fd5b50610aa760048036036040811015610a7157600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061177c565b6040518082815260200191505060405180910390f35b348015610ac957600080fd5b50610b0060048036036040811015610ae057600080fd5b8101908080359060200190929190803590602001909291905050506117d7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b348015610b4e57600080fd5b50610b8560048036036040811015610b6557600080fd5b81019080803590602001909291908035906020019092919050505061183a565b6040518082815260200191505060405180910390f35b348015610ba757600080fd5b50610c9560048036036080811015610bbe57600080fd5b8101908080359060200190640100000000811115610bdb57600080fd5b820183602082011115610bed57600080fd5b80359060200191846001830284011164010000000083111715610c0f57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019092919080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506118c3565b6040518082815260200191505060405180910390f35b348015610cb757600080fd5b50610cee60048036036040811015610cce57600080fd5b810190808035906020019092919080359060200190929190505050611b59565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b348015610d3c57600080fd5b50610d6960048036036020811015610d5357600080fd5b8101908080359060200190929190505050611bbc565b005b348015610d7757600080fd5b50610da460048036036020811015610d8e57600080fd5b8101908080359060200190929190505050611be2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b348015610df257600080fd5b50610e1f60048036036020811015610e0957600080fd5b8101908080359060200190929190505050611c30565b6040518082815260200191505060405180910390f35b348015610e4157600080fd5b50610e8460048036036020811015610e5857600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611c8a565b6040518082815260200191505060405180910390f35b348015610ea657600080fd5b50610ed360048036036020811015610ebd57600080fd5b8101908080359060200190929190505050611e15565b6040518082815260200191505060405180910390f35b610f1560048036036020811015610eff57600080fd5b8101908080359060200190929190505050611e32565b005b348015610f2357600080fd5b50610f5a60048036036040811015610f3a57600080fd5b810190808035906020019092919080359060200190929190505050611ea9565b005b348015610f6857600080fd5b50610fbf60048036036060811015610f7f57600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611ec5565b005b348015610fcd57600080fd5b5061102460048036036060811015610fe457600080fd5b8101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611f54565b005b600061103183611527565b821061103c57600080fd5b60056000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b60008060008154600101919050819055905090565b816008600085815260200190815260200160002060006110bd866113a5565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600960008581526020019081526020016000206000611128866113a5565b815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061118083611bbc565b505050565b816005600085815260200190815260200160002060006111a486611527565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060066000858152602001908152602001600020600061120f86611527565b815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611267836114c4565b505050565b600b6000828152602001908152602001600020600081548092919060019003919050555050565b600080600090506000600190505b60005481116113455783805190602001206003600083815260200190815260200160002060405180828054600181600116156101000203166002900480156113205780601f106112fe576101008083540402835291820191611320565b820191906000526020600020905b81548152906001019060200180831161130c575b50509150506040518091039020141561133857600191505b80806001019150506112a1565b8192505050919050565b806001600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6000600a6000838152602001908152602001600020549050919050565b6000600c600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b80600c600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6000600b6000838152602001908152602001600020549050919050565b600b60008281526020019081526020016000206000815480929190600101919050555050565b806003600084815260200190815260200160002090805190602001906114bf929190611fb7565b505050565b600760008281526020019081526020016000206000815480929190600101919050555050565b60006001600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600060076000838152602001908152602001600020549050919050565b6000806000905060008061155786611455565b905060008092505b818310156115b95761157187846117d7565b90508073ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff1614156115ac57600193505b828060010193505061155f565b8394505050505092915050565b806004600084815260200190815260200160002060006115e585611455565b815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061163a82611472565b5050565b6060600360008381526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116e75780601f106116bc576101008083540402835291602001916116e7565b820191906000526020600020905b8154815290600101906020018083116116ca57829003601f168201915b50505050509050919050565b60006116fe83611527565b821061170957600080fd5b60066000848152602001908152602001600020600083815260200190815260200160002060006117398585611026565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6000600e600084815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b60006117e283611455565b82106117ed57600080fd5b60046000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b6000611845836113a5565b821061185057600080fd5b60096000848152602001908152602001600020600083815260200190815260200160002060006118808585611b59565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b600060648411156118d357606493505b600015156118e086611293565b1515146118ec57600080fd5b60006118f6611089565b90506000308260405161190890612037565b808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050604051809103906000f080158015611961573d6000803e3d6000fd5b50905061196e828261134f565b61197882336115c6565b61198282336113ff565b61198c8288611498565b611997823388611f54565b6119a18286611ea9565b8373ffffffffffffffffffffffffffffffffffffffff16638bfb07c93330856040518463ffffffff1660e01b8152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019350505050600060405180830381600087803b158015611a5c57600080fd5b505af1158015611a70573d6000803e3d6000fd5b505050507f8af4577531b6c53676c2ddaf1b411154b1e1386c340a8ce69494c0a500c68ed387833360405180806020018481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828103825285818151815260200191508051906020019080838360005b83811015611b10578082015181840152602081019050611af5565b50505050905090810190601f168015611b3d5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a18192505050949350505050565b6000611b64836113a5565b8210611b6f57600080fd5b60086000848152602001908152602001600020600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b600a60008281526020019081526020016000206000815480929190600101919050555050565b600060046000838152602001908152602001600020600080815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6000806064905060008090506000611c4785611455565b905060008092505b81831015611c7e57611c6186846117d7565b9050611c6d868261177c565b840393508280600101935050611c4f565b83945050505050919050565b600060606040518060600160405280603281526020016122f060329139905060008373ffffffffffffffffffffffffffffffffffffffff166323fabea433846040518363ffffffff1660e01b8152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611d4a578082015181840152602081019050611d2f565b50505050905090810190601f168015611d775780820380516001836020036101000a031916815260200191505b509350505050602060405180830381600087803b158015611d9757600080fd5b505af1158015611dab573d6000803e3d6000fd5b505050506040513d6020811015611dc157600080fd5b810190808051906020019092919050505090507f30326c174c3f94e2a892ab1498dd967eeb7f8d7efcab9f0feca9bcc19f10175d816040518082815260200191505060405180910390a18092505050919050565b6000600d6000838152602001908152602001600020549050919050565b611e3b81611e15565b341015611e4757600080fd5b6000611e52826113c2565b90508073ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015611e9a573d6000803e3d6000fd5b50611ea582336113ff565b5050565b80600d6000848152602001908152602001600020819055505050565b611ece836113c2565b73ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16148015611f105750611f0c83611c30565b8111155b611f1957600080fd5b60001515611f278484611544565b15151415611f3a57611f3983836115c6565b5b611f4483836113ff565b611f4f838383611f54565b505050565b80600e600085815260200190815260200160002060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282540192505081905550505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611ff857805160ff1916838001178555612026565b82800160010185558215612026579182015b8281111561202557825182559160200191906001019061200a565b5b5090506120339190612044565b5090565b6102868061206a83390190565b61206691905b8082111561206257600081600090555060010161204a565b5090565b9056fe608060405234801561001057600080fd5b506040516102863803806102868339818101604052604081101561003357600080fd5b810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508060018190555050506101e0806100a66000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c8063010a38f514610051578063174ae4431461006f5780634c901b8c146100b3578063c929ccf3146100fd575b600080fd5b61005961012b565b6040518082815260200191505060405180910390f35b6100b16004803603602081101561008557600080fd5b81019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610135565b005b6100bb610178565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6101296004803603602081101561011357600080fd5b81019080803590602001909291905050506101a1565b005b6000600154905090565b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b806001819055505056fea265627a7a72315820b0be30a30eef109e872a9a4a7a3dfb06e8dfc06dd29de5ada978e4db1414744a64736f6c634300051100325265717565737420746f207075626c69736820416273747261637420495020636f72726573706f6e64696e6720776f726b73a265627a7a72315820dd796eed89f30259c68afae4e86950eab089ac42eed786d9202d93864a7bfc6d64736f6c63430005110032";

    public static final String FUNC_ADDONEOWNERNUM = "addOneOwnerNum";

    public static final String FUNC_ADDONEPROJECTNUM = "addOneProjectNum";

    public static final String FUNC_ADDONERIGHTNUM = "addOneRightNum";

    public static final String FUNC_DECREASEONEOWNERNUM = "decreaseOneOwnerNum";

    public static final String FUNC_GETIPFSHASHBYIPTOKENID = "getIpfsHashByIpTokenId";

    public static final String FUNC_GETORIGINALAUTHOR = "getOriginalAuthor";

    public static final String FUNC_GETOWNERBYIPTOKENIDANDINDEX = "getOwnerByIpTokenIdAndIndex";

    public static final String FUNC_GETOWNERNUM = "getOwnerNum";

    public static final String FUNC_GETPRESENTOWNER = "getPresentOwner";

    public static final String FUNC_GETPRICE = "getPrice";

    public static final String FUNC_GETPROJECTCONTRACT = "getProjectContract";

    public static final String FUNC_GETPROJECTID = "getProjectId";

    public static final String FUNC_GETPROJECTNUM = "getProjectNum";

    public static final String FUNC_GETREMAINSHAREOFWORK = "getRemainShareOfWork";

    public static final String FUNC_GETRIGHTCONTRACT = "getRightContract";

    public static final String FUNC_GETRIGHTID = "getRightId";

    public static final String FUNC_GETRIGHTNUM = "getRightNum";

    public static final String FUNC_GETSHAREBYIPTOKENIDOWNER = "getShareByIpTokenIdOwner";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_JUDGE = "judge";

    public static final String FUNC_JUDGEIPIFEXIST = "judgeIpIfExist";

    public static final String FUNC_PURCHASE = "purchase";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_SENDPROPOSALREQUEST = "sendProposalRequest";

    public static final String FUNC_SETIPFSHASHBYIPTOKENID = "setIpfsHashByIpTokenId";

    public static final String FUNC_SETOWNERSHIPBYIPTOKENID = "setOwnerShipByIpTokenId";

    public static final String FUNC_SETPRESENTOWNER = "setPresentOwner";

    public static final String FUNC_SETPRICE = "setPrice";

    public static final String FUNC_SETPROJECTID = "setProjectId";

    public static final String FUNC_SETRIGHTID = "setRightId";

    public static final String FUNC_SETSHAREBYIPTOKENIDOWNER = "setShareByIpTokenIdOwner";

    public static final String FUNC_SETTOKENADDRESS = "setTokenAddress";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event IPTOKEN_EVENT = new Event("ipToken", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event PROPOSAL_EVENT = new Event("proposal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Abstract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Abstract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Abstract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Abstract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<IpTokenEventResponse> getIpTokenEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(IPTOKEN_EVENT, transactionReceipt);
        ArrayList<IpTokenEventResponse> responses = new ArrayList<IpTokenEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            IpTokenEventResponse typedResponse = new IpTokenEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._ipfsHash = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._creator = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<IpTokenEventResponse> ipTokenEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, IpTokenEventResponse>() {
            @Override
            public IpTokenEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(IPTOKEN_EVENT, log);
                IpTokenEventResponse typedResponse = new IpTokenEventResponse();
                typedResponse.log = log;
                typedResponse._ipfsHash = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._creator = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<IpTokenEventResponse> ipTokenEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(IPTOKEN_EVENT));
        return ipTokenEventFlowable(filter);
    }

    public List<ProposalEventResponse> getProposalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROPOSAL_EVENT, transactionReceipt);
        ArrayList<ProposalEventResponse> responses = new ArrayList<ProposalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalEventResponse typedResponse = new ProposalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProposalEventResponse> proposalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProposalEventResponse>() {
            @Override
            public ProposalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROPOSAL_EVENT, log);
                ProposalEventResponse typedResponse = new ProposalEventResponse();
                typedResponse.log = log;
                typedResponse._proposalId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ProposalEventResponse> proposalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSAL_EVENT));
        return proposalEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addOneOwnerNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDONEOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addOneProjectNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDONEPROJECTNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addOneRightNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDONERIGHTNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseOneOwnerNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEONEOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getIpfsHashByIpTokenId(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETIPFSHASHBYIPTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOriginalAuthor(BigInteger _ipTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETORIGINALAUTHOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getOwnerByIpTokenIdAndIndex(BigInteger _ipTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERBYIPTOKENIDANDINDEX, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getOwnerNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNERNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPresentOwner(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getPrice(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getProjectContract(BigInteger _ipTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROJECTCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getProjectId(BigInteger _ipTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROJECTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getProjectNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPROJECTNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getRemainShareOfWork(BigInteger _ipTokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREMAINSHAREOFWORK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getRightContract(BigInteger _ipTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRIGHTCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getRightId(BigInteger _ipTokenId, BigInteger _index) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRIGHTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getRightNum(BigInteger _ipTokenID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRIGHTNUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getShareByIpTokenIdOwner(BigInteger _ipTokenId, String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETSHAREBYIPTOKENIDOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
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

    public RemoteFunctionCall<Boolean> judge(BigInteger _ipTokenId, String _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_JUDGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _to)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> judgeIpIfExist(String _ipfsHash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_JUDGEIPIFEXIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_ipfsHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> purchase(BigInteger _tokenId, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PURCHASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String _ipfsHash, BigInteger _share, BigInteger _price, String _committee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_ipfsHash), 
                new org.web3j.abi.datatypes.generated.Uint256(_share), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.Address(160, _committee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sendProposalRequest(String _committee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SENDPROPOSALREQUEST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _committee)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setIpfsHashByIpTokenId(BigInteger _ipTokenID, String _ipfsHash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETIPFSHASHBYIPTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID), 
                new org.web3j.abi.datatypes.Utf8String(_ipfsHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwnerShipByIpTokenId(BigInteger _ipTokenId, String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETOWNERSHIPBYIPTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _owner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPresentOwner(BigInteger _ipTokenID, String _presentOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPRESENTOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID), 
                new org.web3j.abi.datatypes.Address(160, _presentOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPrice(BigInteger _ipTokenID, BigInteger _price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPRICE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setProjectId(BigInteger _ipTokenId, String _projectContract, BigInteger _projectId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPROJECTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _projectContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_projectId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setRightId(BigInteger _ipTokenId, String _rightContract, BigInteger _rightId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRIGHTID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _rightContract), 
                new org.web3j.abi.datatypes.generated.Uint256(_rightId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setShareByIpTokenIdOwner(BigInteger _ipTokenId, String _owner, BigInteger _share) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETSHAREBYIPTOKENIDOWNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenId), 
                new org.web3j.abi.datatypes.Address(160, _owner), 
                new org.web3j.abi.datatypes.generated.Uint256(_share)), 
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

    public RemoteFunctionCall<TransactionReceipt> transfer(BigInteger _ipTokenID, String _to, BigInteger _share) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_ipTokenID), 
                new org.web3j.abi.datatypes.Address(160, _to), 
                new org.web3j.abi.datatypes.generated.Uint256(_share)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Abstract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Abstract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Abstract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Abstract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Abstract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Abstract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Abstract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Abstract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Abstract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Abstract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Abstract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Abstract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Abstract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Abstract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Abstract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Abstract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class IpTokenEventResponse extends BaseEventResponse {
        public String _ipfsHash;

        public BigInteger _tokenId;

        public String _creator;
    }

    public static class ProposalEventResponse extends BaseEventResponse {
        public BigInteger _proposalId;
    }
}
