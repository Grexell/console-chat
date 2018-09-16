package by.dima.model.logic.service;

import java.util.List;

public interface RangeInfoService extends InfoService {
    List<String> getAllAgentIDs(int from, int length);

    List<String> getFreeAgentIDs(int from, int length);

    List<String> getOpenedChatIDs(int from, int length);

    List<String> getQueuedUserIDs(int from, int length);
}
