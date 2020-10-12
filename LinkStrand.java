//Kyra Cooperman, Julia Rosner, Darien Minnihan
public class LinkStrand implements IDnaStrand {
    private class Node {
        String info;
        Node next;
        public Node(String s) {
            info = s;
            next = null;
        }
    }
    private Node myFirst,myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private int myLocalIndex;
    private Node myCurrent;
    public LinkStrand() {
        this("");

    }
    public LinkStrand(String s) {
        initialize(s);
    }

    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder();
        Node firstNode = myFirst;
        while (firstNode != null) {
            retString.append(firstNode.info);
            firstNode = firstNode.next;
        }
        return retString.toString();
    }

    @Override
    public long size() {
        //long size = mySize;
        //while(myCurrent != null){
          //  mySize++;
           // myCurrent = myCurrent.next;
        //}
        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst = new Node(source);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myCurrent = myFirst;
        myIndex = 0;
        myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);

    }

    @Override
    public IDnaStrand append(String dna) {
        this.myLast.next = new Node(dna);
        this.myLast = this.myLast.next;
        this.mySize += dna.length();
        this.myAppends += 1;
        return this;
    }
    private void frontAppend(String s){
        Node n = new Node(s);
        this.mySize += s.length();
        n.next = myFirst;
        myFirst = n;
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand ret = new LinkStrand();
        //if (myFirst == null) return ret;
        Node current = myFirst;
        while(current!=null) {
            StringBuilder rev = new StringBuilder(current.info);
            String s = rev.reverse().toString();
            ret.frontAppend(s);
            current = current.next;
        }
        return ret;

    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if(index<0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }
        if(index<= myIndex){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while(index != myIndex){
            myIndex ++;
            myLocalIndex++;
            if(myLocalIndex >= myCurrent.info.length()){
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }

        return myCurrent.info.charAt(myLocalIndex);
    }


}