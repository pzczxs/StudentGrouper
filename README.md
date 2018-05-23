# StudentGrouper
Grouping randomly the students into several groups

## 1. Introduction
Given a student set, this tool can group randomly these students into several groups, so that each group has approximately equal male and female studens.

## 2. License
StudentGrouper is a free JAVA toolbox; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

StudentGrouper is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with MLSSVR; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.

## 2. How to Use StudentGrouper

### 2.1. Command Line & Input Parameters
```
> java -jar StudentGrouper.jar [-nfold num] [-seed num] -file input_file
    -nfold num: the number of groups (default 2)
    -seed num: random seed (default 0)
    -file input_file: student name list seperated by tab. The file format is referred to students.txt
```
### 2.2. Examples
```
> java -jar StudentGrouper.jar -nfold 3 -seed 20180523 -file students.txt
```

### 2.3. Additional Information

This toolbox is written by [XU, Shuo](http://54xushuo.net/wiki/) from [Beijing University of Technology](http://www.bjut.edu.cn). 

For any question, please contact XU, Shuo at <xushuo@bjut.edu.cn> OR <pzczxs@gmail.com>.
