#include<iostream>
#include<string.h>
#include<vector>
#include<map>
#include<algorithm>
using namespace std;

int main()
{
	string s,curr_str;
	int mtu;
	cout<<"Enter the string:";
	cin>>s;
	cout<<"Enter MTU:";
	cin>>mtu;
	map<int,string> mp;
	vector<int> vec;
	int id=1;
	cout<<"Frames:"<<endl;
	for(int i=0;i<s.length();i+=mtu)
	{	
		if(i+mtu<s.length())
			curr_str=s.substr(i,mtu);
		else
			curr_str=s.substr(i,s.length()-i);
		cout<<curr_str<<endl;
		mp[id]=curr_str;
		vec.push_back(id);
		id+=1;
	}
	random_shuffle(vec.begin(),vec.end());
	sort(vec.begin(),vec.end());
	string st="";
	for(auto i:vec)
		st+=mp[i];
	cout<<"final string:"<<st<<endl;
}
