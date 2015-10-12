function getSubjectOfChannel(xchannel_id, type) {
	var url = "";
	if (type == "C") {
		url = "/istore/servlet/b2b/references/list.do?storeId=0&xchannelId="
				+ xchannel_id;
	}
	if (type == "N") {
		url = "/istore/servlet/b2b/news/list.do?storeId=0&xchannelId="
				+ xchannel_id;
	}
	window.location.href = url;
}