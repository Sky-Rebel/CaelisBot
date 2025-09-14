#include <fstream>
#include <iostream>
#include <string>

using namespace std;

int fileOper()
{
    ofstream outputFileStream;
    outputFileStream.open("aaa.txt", ios::app);
    outputFileStream << "Hello World";
    outputFileStream.close();
    ifstream inputFileStream;
    inputFileStream.open("aaa.txt", ios::in);
    string line;
    getline(inputFileStream, line);
    cout << line << endl;
    outputFileStream.close();
    return 0;
}