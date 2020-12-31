#include <iostream>
using namespace std;
#define MAX_NODES 100
class node
{
	public:int distances[MAX_NODES];
};
int main()
{
  cout<<"Enter the total number of nodes: ";
  int n,i,j,k;
  cin>>n;
  cout<<"Enter cost matrix:\n";
  int cost[n][n];
  node* nodes=new node[n];
  for(i=0;i<n;i++)
  {
    for(j=0;j<n;j++)
    {
      int temp;
      cin>>temp;
      cost[i][j]=temp;
      nodes[i].distances[j]=cost[i][j];
      if(i==j)
	      nodes[i].distances[j]=0;
    }
  }
  int updation_count;
  do
  {
    updation_count=0;
    for(i=0;i<n;i++)
    {
      for(j=0;j<n;j++)
      {
        for(k=0;k<n;k++)
        {
          if(nodes[i].distances[j]>nodes[i].distances[k]+nodes[k].distances[j])
          {
            updation_count++;
            nodes[i].distances[j]=nodes[i].distances[k]+nodes[k].distances[j];
          }
        }
      }
    }
  }while(updation_count>0);
  for(int i=0;i<n;i++)
  {
    cout<<char('a'+i)<<"'s table"<<endl;
    for(int j=0;j<n;j++)
    {
      cout<<char('a'+i)<<"->"<<char('a'+j)<<": "<<nodes[i].distances[j]<<endl;
    }
  }
  return 0;
}
