package imult;

public class MemoryFarm {
  // farm = 2D array
  // allocate() method keeps track of index
  public static Unsigned[][] farm_ = null;
  private static int curIdx_ = -1;
  private static int size_ = 30 * BigInt.MAX_LENGTH_;
  private static boolean init_ = false;
  private MemoryFarm() {
    //disable instantiation
  }
  public static void initMem() {
    if(!init_) {
      init_ = true;
      System.out.println("Initialising scratch memory..."); 
      farm_ = new Unsigned[size_][BigInt.MAX_LENGTH_];
      for(int i = 0; i < size_; i++)
        for(int j = 0; j < BigInt.MAX_LENGTH_; j++)
          farm_[i][j] = BigInt.Zero_;
    }
  }
  public static Unsigned[] alloc() {
    if(curIdx_ + 1 < size_) 
      curIdx_++;
    else
      curIdx_ = 0;
    return farm_[curIdx_];
  }
  public static void dealloc() {
    //System.out.println("curIdx_=" + curIdx_);
    for(int i = 0; i < size_; i++)
      for(int j = 0; j < BigInt.MAX_LENGTH_; j++)
        farm_[i][j] = BigInt.Zero_;
    curIdx_ = -1;
  }
  private static void clearRow(int row) {
    for(int i = 0; i < BigInt.MAX_LENGTH_; i++) {
      farm_[row][i] = BigInt.Zero_; 
    }
  }
}
