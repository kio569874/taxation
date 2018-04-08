indexTaxationApp.controller("TaxationIndexController",function(){
	this.serviceInfoList = serviceInfoList;
});

var serviceInfoList = [
	{
		id:"0001",
		serviceType:"税务代理",
		providerLevel:"三星",
		context:"五年专业税务代理经验，给纳税人最好的体验",
		evaluate:20,
		price:1000,
		visible:true,
		isShow:true
	},
	{
		id:"0002",
		serviceType:"税务代理",
		providerLevel:"四星",
		context:"五年专业税务代理经验，给纳税人最好的体验",
		evaluate:20,
		price:1000,
		visible:true,
		isShow:true
	},
	{
		id:"0003",
		serviceType:"税务代理",
		providerLevel:"五星",
		context:"十年专业税务代理经验，给纳税人最好的体验",
		evaluate:20,
		price:1000,
		visible:true,
		isShow:true
	}
];